# Tarea 1 · Inventario

**Entrega:** miércoles 18 de noviembre, 11:59 p.m. · link de tu repo de GitHub en el grupo del curso.

## Qué hacer

En tu proyecto `orders-domain`, crea la clase `pe.intevex.orders.tarea.Inventario` con dos métodos:

### 1. `valorTotal(List<Product> productos)`

Devuelve el valor total del inventario: la suma de `precio × stock` de cada producto, como `BigDecimal`.

Con este catálogo:

| SKU | Producto | Precio | Stock |
|---|---|---|---|
| KB-001 | Teclado mecánico | 199.90 | 15 |
| MS-002 | Mouse inalámbrico | 79.90 | 3 |
| MN-003 | Monitor 27" | 1099.00 | 5 |
| HS-004 | Audífonos gamer | 249.50 | 0 |

el resultado debe ser **S/ 8733.20**.

### 2. `conPocoStock(List<Product> productos, int umbral)`

Devuelve la lista de productos cuyo stock es **menor o igual** al umbral.

Con `umbral = 5`, debe devolver: Mouse inalámbrico, Monitor 27" y Audífonos gamer.

## Reglas

- Usa `BigDecimal` para el dinero. Nada de `double`.
- Resuélvelo con bucles `for`. (Streams lo vemos en la sesión 3, y ahí vas a reescribir esto en 2 líneas.)
- Llama a los dos métodos desde `Main` y muestra el resultado en consola.
- Sube tu código a GitHub: un repo público llamado `orders-domain`.

## Extra (opcional)

Agrega un método `agotados(List<Product> productos)` que devuelva solo los productos con stock 0.
