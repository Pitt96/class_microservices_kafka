package pe.intevex.orders.tarea;

import pe.intevex.orders.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    /** Valor total del inventario = suma de (precio × stock) de cada producto. */
    public static BigDecimal valorTotal(List<Product> productos) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : productos) {
            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(product.getStock()));
            total = total.add(subTotal);
        }

        return total;
    }

    /** Productos cuyo stock es menor o igual al umbral. */
    public static List<Product> conPocoStock(List<Product> productos, int umbral) {
        List<Product> filterProducts = new ArrayList<>();
        for (Product product : productos) {
            if (product.getStock() <= umbral){
                filterProducts.add(product);
            }
        }

        return filterProducts;
    }

    public static List<Product> agotados(List<Product> productos){
        List<Product> filterProducts = new ArrayList<>();
        for (Product product : productos) {
            if (product.getStock() == 0){
                filterProducts.add(product);
            }
        }
        return filterProducts;
    }
}
