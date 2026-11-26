package pe.intevex.orders.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) implements Comparable<Money>{
    public static final String PEN = "PEN";

    public Money{
        Objects.requireNonNull(amount,"El monto es obligatorio");
        if(currency == null || currency.isBlank()){
            throw new IllegalArgumentException("La moneda es oblihatoria");
        }

        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money[] splitIgv(BigDecimal tasa) {
        Objects.requireNonNull(tasa, "La tasa de IGV es obligatoria");

        if (tasa.signum() < 0) {
            throw new IllegalArgumentException("La tasa no puede ser negativa");
        }

        // 1. Calcular la base imponible
        BigDecimal factor = BigDecimal.ONE.add(tasa);

        BigDecimal base = amount.divide(
                factor,
                2,
                RoundingMode.HALF_UP
        );

        // 2. Calcular el IGV restando la base al total
        BigDecimal igv = amount.subtract(base);

        // 3. Devolver ambos montos
        return new Money[]{
                new Money(base, currency),
                new Money(igv, currency)
        };
    }

    public List<Money> split(int partes){
        if (partes <= 0) {
            throw new IllegalArgumentException("Las partes deben ser mayores a cero");
        }

        List<Money> listPartes = new ArrayList<>();

        // 1. Calcular cuánto recibe cada persona inicialmente
        BigDecimal parteBase = amount.divide(
                BigDecimal.valueOf(partes),
                2,
                RoundingMode.DOWN
        );

        // 2. Calcular cuánto dinero sobra
        BigDecimal sobrante = amount.subtract(
                parteBase.multiply(BigDecimal.valueOf(partes))
        );

        // 3. Convertir el sobrante a céntimos
        int centimos = sobrante.movePointRight(2).intValueExact();

        // 4. Repartir el dinero
        for (int i = 0; i < partes; i++) {
            BigDecimal montoFinal = parteBase;

            if (i < centimos) {
                montoFinal = montoFinal.add(new BigDecimal("0.01"));
            }

            listPartes.add(new Money(montoFinal, currency));
        }


        return listPartes;
    }

    public static Money sumList(List<Money> montos){
        Money montoSumado = zero();
        if(isNullOrEmpty(montos)){
            return montoSumado;
        }

        for (Money money: montos){
            montoSumado = montoSumado.add(money);
        }

        return montoSumado;
    }

    public static Money sumList(List<Money> montos, String currency) {
        Money montoSumado = new Money(BigDecimal.ZERO, currency);

        if(isNullOrEmpty(montos)){
            return montoSumado;
        }

        for (Money money : montos) {
            if (money.currency().equals(currency)) {
                montoSumado = montoSumado.add(money);
            }
        }

        return montoSumado;
    }

    public static Money of(String amount, String currency) {
        return new Money(new BigDecimal(amount), currency);
    }

    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }

    public static Money soles(String amount) {
        return new Money(new BigDecimal(amount), PEN);
    }

    public static Money soles(BigDecimal amount) {
        return new Money(amount, PEN);
    }

    public static Money zero() {
        return soles(BigDecimal.ZERO);
    }

    public Money add(Money other) {
        checkSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        checkSameCurrency(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    public Money multiply(int quantity) {
        return new Money(amount.multiply(BigDecimal.valueOf(quantity)), currency);
    }

    /** Porcentaje del monto. Ej.: 0.10 = 10%. */
    public Money percentage(BigDecimal rate) {
        return new Money(amount.multiply(rate), currency);
    }

    public boolean isGreaterOrEqualThan(Money other) {
        checkSameCurrency(other);
        return amount.compareTo(other.amount) >= 0;
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    private void checkSameCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("No se pueden operar monedas distintas: " + currency + " y " + other.currency);
        }
    }

    private static boolean isNullOrEmpty(List<Money> montos) {
        return montos == null || montos.isEmpty();
    }

    @Override
    public String toString() {
        return (PEN.equals(currency) ? "S/ " : currency + " ") + amount.toPlainString();
    }

    @Override
    public int compareTo(Money other) {
        checkSameCurrency(other);
        return amount.compareTo(other.amount);
    }
}
