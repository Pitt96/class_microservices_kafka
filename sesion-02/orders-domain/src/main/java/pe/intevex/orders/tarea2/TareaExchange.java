package pe.intevex.orders.tarea2;

import pe.intevex.orders.domain.ExchangeRate;
import pe.intevex.orders.domain.Money;

public class TareaExchange {
    public static void main(String[] args) {
        // 1. Crear tipo de cambio
        ExchangeRate usdPen = ExchangeRate.of(" usd ", " pen ", "3.75");

        System.out.println(usdPen);
        // 1 USD = 3.7500 PEN

        // 2. Invertir tipo de cambio
        ExchangeRate penUsd = usdPen.inverse();

        System.out.println(penUsd);
        // 1 PEN = 0.2667 USD

        // 3. Crear dinero en dólares
        Money dolares = Money.of("100", "USD");

        // 4. Convertir dólares a soles
        Money soles = usdPen.convert(dolares);

        System.out.println("Dólares: " + dolares);
        // Dólares: USD 100.00

        System.out.println("Soles: " + soles);
        // Soles: S/ 375.00

        // 5. Convertir soles a dólares
        Money resultado = penUsd.convert(soles);

        System.out.println("Resultado: " + resultado);
        // Resultado: USD 100.00
    }
}
