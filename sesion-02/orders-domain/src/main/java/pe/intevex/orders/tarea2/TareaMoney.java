package pe.intevex.orders.tarea2;

import pe.intevex.orders.domain.ExchangeRate;
import pe.intevex.orders.domain.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TareaMoney {
    public static void main(String[] args) {
        //pruebaSuma();
        //pruebaSplit();
        //pruebaSplitIGV();
        //pruebaCompareto();
    }

    public static void pruebaSuma(){
        List<Money> montos = List.of(
                Money.soles("199.90"),
                Money.soles("79.90"),
                Money.soles("1099.00")
        );

        // PRUEBA 1: Comportamiento incorrecto
        Money totalIncorrecto = Money.zero();

        for (Money m : montos) {
            totalIncorrecto.add(m);
        }

        // No reasignar el resultado provoca que totalIncorrecto conserve su valor original.
        System.out.println("Sin reasignar: " + totalIncorrecto);


        // PRUEBA 2: Suma correcta
        Money total = Money.sumList(montos);

        System.out.println("Suma correcta: " + total);


        // PRUEBA 3: Lista vacía
        Money totalVacio = Money.sumList(List.of());

        System.out.println("Lista vacía: " + totalVacio);
    }

    public static void pruebaSplit(){
        // PRUEBA 1: Dividir S/ 100.00 entre 3
        Money original1 = Money.soles("100.00");

        List<Money> partes1 = original1.split(3);

        System.out.println("\n--- PRUEBA 1 ---");
        System.out.println("Monto original: " + original1);
        System.out.println("Partes: " + partes1);

        Money total1 = Money.sumList(partes1);

        System.out.println("Suma de las partes: " + total1);
        System.out.println("¿Es igual al original? " +
                (total1.amount().compareTo(original1.amount()) == 0));


        // PRUEBA 2: División exacta
        Money original2 = Money.soles("10.00");

        List<Money> partes2 = original2.split(4);

        System.out.println("\n--- PRUEBA 2 ---");
        System.out.println("Monto original: " + original2);
        System.out.println("Partes: " + partes2);

        Money total2 = Money.sumList(partes2);

        System.out.println("Suma de las partes: " + total2);
        System.out.println("¿Es igual al original? " +
                (total2.amount().compareTo(original2.amount()) == 0));


        // PRUEBA 3: Dividir S/ 10.00 entre 3
        Money original3 = Money.soles("10.00");

        List<Money> partes3 = original3.split(3);

        System.out.println("\n--- PRUEBA 3 ---");
        System.out.println("Monto original: " + original3);
        System.out.println("Partes: " + partes3);

        Money total3 = Money.sumList(partes3);

        System.out.println("Suma de las partes: " + total3);
        System.out.println("¿Es igual al original? " +
                (total3.amount().compareTo(original3.amount()) == 0));


        // PRUEBA 4: Validar cantidad de partes incorrecta
        System.out.println("\n--- PRUEBA 4 ---");

        try {
            original1.split(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción controlada: " + e.getMessage());
        }
    }

    public static void pruebaSplitIGV(){
        // PRUEBA 1: Monto que incluye IGV exactamente
        Money total1 = Money.soles("118.00");

        Money[] resultado1 = total1.splitIgv(new BigDecimal("0.18"));

        System.out.println("\n--- PRUEBA 1 ---");
        System.out.println("Total: " + total1);
        System.out.println("Base imponible: " + resultado1[0]);
        System.out.println("IGV: " + resultado1[1]);

        System.out.println("Suma: " +
                Money.sumList(List.of(resultado1)));

        // PRUEBA 2: Monto que requiere redondeo
        Money total2 = Money.soles("100.00");

        Money[] resultado2 = total2.splitIgv(new BigDecimal("0.18"));

        System.out.println("\n--- PRUEBA 2 ---");
        System.out.println("Total: " + total2);
        System.out.println("Base imponible: " + resultado2[0]);
        System.out.println("IGV: " + resultado2[1]);

        System.out.println("Suma: " +
                Money.sumList(List.of(resultado2)));


        // PRUEBA 3: Comprobar el problema de multiplicar
        System.out.println("\n--- PRUEBA 3 ---");

        Money base = resultado2[0];

        // NO CONVIENE: multiplicar la base por la tasa puede generar
        // diferencias de céntimos debido al redondeo independiente.
        Money igvIncorrecto = base.percentage(new BigDecimal("0.18"));

        Money sumaIncorrecta = base.add(igvIncorrecto);

        System.out.println("Base: " + base);
        System.out.println("IGV calculado por multiplicación: " + igvIncorrecto);
        System.out.println("Suma incorrecta: " + sumaIncorrecta);
    }

    public static void pruebaCompareto(){
        // PRUEBA 1: Ordenar precios
        List<Money> precios = new ArrayList<>(List.of(
                Money.soles("50"),
                Money.soles("9.90"),
                Money.soles("120.5"),
                Money.soles("9.9")
        ));

        System.out.println("--- PRUEBA 1: Collections.sort() ---");

        Collections.sort(precios);

        System.out.println("Precios ordenados: " + precios);


        // PRUEBA 2: Obtener el precio máximo
        System.out.println("\n--- PRUEBA 2: Collections.max() ---");

        Money precioMaximo = Collections.max(precios);

        System.out.println("Precio máximo: " + precioMaximo);


        // PRUEBA 3: Comparar monedas diferentes
        System.out.println("\n--- PRUEBA 3: Diferentes monedas ---");

        try {
            Money soles = Money.soles("100");
            Money dolares = new Money(new BigDecimal("100"), "USD");

            System.out.println(soles.compareTo(dolares));

        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }


        // PRUEBA 4: Comparación con equals()
        System.out.println("\n--- PRUEBA 4: Comparación de BigDecimal ---");

        System.out.println(
                Money.soles("10").equals(Money.soles("10.00"))
        );

        System.out.println(
                new BigDecimal("10").equals(new BigDecimal("10.00"))
        );
    }
}
