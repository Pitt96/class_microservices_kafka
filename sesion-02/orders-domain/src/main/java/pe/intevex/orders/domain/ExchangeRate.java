package pe.intevex.orders.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Objects;

public record ExchangeRate(String from, String to, BigDecimal rate) {
    public ExchangeRate {
        // 1. Normalizar las monedas
        from = normalizeCurrency(from);
        to = normalizeCurrency(to);

        // 2. Validar que las monedas sean distintas
        if (from.equals(to)) {
            throw new IllegalArgumentException(
                    "Las monedas deben ser distintas"
            );
        }

        // 3. Validar el tipo de cambio
        if (rate == null || rate.signum() <= 0) {
            throw new IllegalArgumentException(
                    "El tipo de cambio debe ser mayor a 0"
            );
        }

        // 4. Normalizar a 4 decimales
        rate = rate.setScale(4, RoundingMode.HALF_UP);

        if (rate.signum() <= 0) {
            throw new IllegalArgumentException(
                    "El tipo de cambio debe ser mayor a 0"
            );
        }
    }

    // Normalizar y validar el código de moneda
    private static String normalizeCurrency(String currency) {
        String normalized = currency == null
                ? ""
                : currency.trim().toUpperCase(Locale.ROOT);

        if (!normalized.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException(
                    "Moneda inválida: " + currency
            );
        }

        return normalized;
    }

    // Método de fábrica
    public static ExchangeRate of(String from, String to, String rate) {
        return new ExchangeRate(
                from,
                to,
                new BigDecimal(rate)
        );
    }

    // Invertir el tipo de cambio
    public ExchangeRate inverse() {
        BigDecimal inverseRate = BigDecimal.ONE.divide(
                rate,
                4,
                RoundingMode.HALF_UP
        );

        return new ExchangeRate(to, from, inverseRate);
    }

    // Convertir una moneda
    public Money convert(Money monto) {
        Objects.requireNonNull(monto, "El monto es obligatorio");

        if (!monto.currency().equals(from)) {
            throw new IllegalArgumentException(
                    "Este tipo de cambio convierte " + from +
                            ", no " + monto.currency()
            );
        }

        BigDecimal convertedAmount = monto.amount().multiply(rate);

        return Money.of(convertedAmount.toPlainString(), to);
    }

    @Override
    public String toString() {
        return "1 " + from + " = " + rate.toPlainString() + " " + to;
    }
}
