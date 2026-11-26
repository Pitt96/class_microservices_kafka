package pe.intevex.orders.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Un producto que vende la tienda.
 * El precio es BigDecimal: el dinero NUNCA se guarda en double.
 */
public class Product {

    private final String sku;
    private final String name;
    private final BigDecimal price;
    private int stock;

    public Product(String sku, String name, BigDecimal price, int stock) {

        if(sku == null || sku.isBlank()){
            throw new IllegalArgumentException("El SKU es obligatorio");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("El name es obligatorio");
        }
        if(price == null || price.signum() <=0){
            throw new IllegalArgumentException("El precio debe ser mayor 0");
        }
        if(stock < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /** Método de fábrica estático: más fácil de leer que el constructor. */
    public static Product of(String sku, String name, String price, int stock) {
        return new Product(sku, name, new BigDecimal(price), stock);
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    /** Dos productos son el mismo si tienen el mismo SKU. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return sku.equals(other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "%s | %s | S/ %s | stock %d".formatted(sku, name, price, stock);
    }
}