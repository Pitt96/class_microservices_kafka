package pe.intevex.orders.tarea2;

import pe.intevex.orders.domain.ExchangeRate;
import pe.intevex.orders.domain.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyDemo {
    public static void main(String[] args) {
        // =========================================
        // 1. SUMA
        // =========================================
        System.out.println("=== 1. Suma ===");

        List<Money> montos = List.of(
                Money.soles("125.50"),
                Money.soles("250.30"),
                Money.soles("1000.00"),
                Money.soles("3.00")
        );

        System.out.println(Money.sumList(montos));
        System.out.println(Money.sumList(null));


        // =========================================
        // 2. REPARTIR
        // =========================================
        System.out.println("\n=== 2. Repartir ===");

        Money cien = Money.soles("100");

        List<Money> reparto = cien.split(3);
        System.out.println(reparto);

        Money diez = Money.soles("10");

        List<Money> repartoDiez = diez.split(4);
        System.out.println(repartoDiez);

        System.out.println(
                "Suma de las partes: " + Money.sumList(reparto)
        );


        // =========================================
        // 3. IGV
        // =========================================
        System.out.println("\n=== 3. IGV ===");

        BigDecimal igvRate = new BigDecimal("0.18");

        Money precioConIgv1 = Money.soles("118");
        Money[] resultado1 = precioConIgv1.splitIgv(igvRate);

        System.out.println(
                precioConIgv1 +
                        " -> base " + resultado1[0] +
                        " | IGV " + resultado1[1]
        );

        Money precioConIgv2 = Money.soles("100");
        Money[] resultado2 = precioConIgv2.splitIgv(igvRate);

        System.out.println(
                precioConIgv2 +
                        " -> base " + resultado2[0] +
                        " | IGV " + resultado2[1]
        );


        // =========================================
        // 4. ORDENAR Y COMPARAR
        // =========================================
        System.out.println("\n=== 4. Ordenar y comparar ===");

        List<Money> precios = new ArrayList<>(List.of(
                Money.soles("50"),
                Money.soles("9.90"),
                Money.soles("120.5"),
                Money.soles("9.9")
        ));

        Collections.sort(precios);

        System.out.println(precios);

        Money mayor = Collections.max(precios);

        System.out.println("Mayor: " + mayor);

        Money money1 = Money.soles("9.90");
        Money money2 = Money.soles("9.9");

        System.out.println("Money equals: " + money1.equals(money2));

        BigDecimal decimal1 = new BigDecimal("9.90");
        BigDecimal decimal2 = new BigDecimal("9.9");

        System.out.println(
                "BigDecimal equals: " + decimal1.equals(decimal2)
        );


        // =========================================
        // 5. TIPO DE CAMBIO
        // =========================================
        System.out.println("\n=== 5. Tipo de cambio ===");

        ExchangeRate usdPen = ExchangeRate.of("USD", "PEN", "3.75");

        System.out.println(usdPen);

        Money dolares = Money.of("100", "USD");

        Money soles = usdPen.convert(dolares);

        System.out.println(dolares + " -> " + soles);

        ExchangeRate penUsd = usdPen.inverse();

        System.out.println(penUsd);

        Money dolaresConvertidos = penUsd.convert(soles);

        System.out.println(soles + " -> " + dolaresConvertidos);


        // =========================================
        // 6. VALIDACIONES Y ERRORES
        // =========================================
        try {
            usdPen.convert(Money.soles("100"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ExchangeRate.of("USD", "USD", "3.75");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ExchangeRate.of("USD", "PEN", "0");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ExchangeRate.of("DOLAR", "PEN", "3.75");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
