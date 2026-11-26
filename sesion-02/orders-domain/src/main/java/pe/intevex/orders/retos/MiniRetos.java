package pe.intevex.orders.retos;

import java.math.BigDecimal;

/** Mini retos del bloque de sintaxis. Completa los TODO. */
public class MiniRetos {

    /**
     * Reto 1: calcula el IGV (18%) de un subtotal.
     * Pista: usa BigDecimal, nunca double. Redondea a 2 decimales.
     * Ejemplo: 250.00 -> 45.00
     */
    public static BigDecimal calcularIgv(BigDecimal subtotal) {
        // TODO
        throw new UnsupportedOperationException("Completa el reto 1");
    }

    /**
     * Reto 2: devuelve el porcentaje de descuento según el tipo de cliente,
     * usando una switch expression.
     *   NEW      -> 0%
     *   FREQUENT -> 5%
     *   VIP      -> 10%
     * (Primero crea el enum CustomerType en el paquete domain.)
     */
    // TODO: public static BigDecimal porcentajeDescuento(CustomerType tipo) { ... }

    public static void main(String[] args) {
        System.out.println("IGV de S/ 250.00: " + calcularIgv(new BigDecimal("250.00")));
    }
}
