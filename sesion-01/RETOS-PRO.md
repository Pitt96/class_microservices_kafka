# Retos Pro · Sesión 1

Para quienes ya dominan Java o terminan antes. Hazlos en orden, a tu ritmo, mientras la clase avanza.
Si te trabas, pregunta en el chat: no hay respuestas tontas.

---

## Reto Pro 1 · Java 21 sin crear un proyecto (bloque Entorno)

Mientras los demás instalan, abre una terminal y escribe `jshell`. Es una consola interactiva de Java.

Prueba esto, línea por línea:

```java
var precio = new java.math.BigDecimal("199.90");
record Punto(int x, int y) {}
new Punto(1, 2)
String dia = switch (java.time.LocalDate.now().getDayOfWeek()) { case SATURDAY, SUNDAY -> "Finde"; default -> "Semana"; };
```

**Resultado esperado:** ver que Java 21 te deja probar código sin `public static void main`.
**Para pensar:** ¿qué te imprime el record `Punto` sin que escribas un `toString()`?

---

## Reto Pro 2 · Tu primer test con JUnit 5 (bloque Maven)

1. Agrega JUnit 5 al `pom.xml`:
   - dentro de `<dependencies>`: `org.junit.jupiter:junit-jupiter:5.11.3` con `<scope>test</scope>`
   - dentro de `<build><plugins>`: `maven-surefire-plugin` versión `3.5.2`
2. Crea `src/test/java/pe/intevex/orders/retos/MiniRetosTest.java`.
3. Escribe un test que verifique que el IGV de `279.80` es `50.36`.
4. Ejecuta `mvn test`.

**Resultado esperado:** `Tests run: 1, Failures: 0`.
**Pista:** `assertEquals(new BigDecimal("50.36"), MiniRetos.calcularIgv(new BigDecimal("279.80")));`
**Ojo:** `new BigDecimal("50.36")` y `new BigDecimal("50.360")` NO son `equals`. ¿Por qué? (busca `compareTo`).

---

## Reto Pro 3 · Clasificar pedidos con pattern matching (bloque Mini retos)

Crea un método `clasificar(BigDecimal monto)` que devuelva:

| Monto | Clasificación |
|---|---|
| 0 o menos | `INVALIDO` |
| menos de S/ 100 | `PEQUENO` |
| menos de S/ 500 | `MEDIANO` |
| S/ 500 o más | `GRANDE` |

**Condición:** sin `if`. Usa un `switch` con **pattern matching y guardas** (Java 21):

```java
return switch (monto) {
    case BigDecimal m when m.signum() <= 0 -> "INVALIDO";
    // ...
};
```

**Para pensar:** ¿por qué el último `case` no puede llevar `when`?

---

## Reto Pro 4 · Product como record (bloque Primeras clases)

Adelántate a la sesión 2: escribe `Product` como un **record** y valida en el **constructor compacto**:

- `sku` y `name` no pueden ser nulos ni vacíos
- `price` debe ser mayor a 0
- `stock` no puede ser negativo

Si algo no cumple, lanza `IllegalArgumentException` con un mensaje claro.

**Resultado esperado:** `new Product("", "Teclado", BigDecimal.TEN, 1)` lanza la excepción `El SKU es obligatorio`.
**Para pensar:** si un record es inmutable, ¿cómo harías para descontar stock cuando alguien compra?
(Esa pregunta la respondemos en la sesión 2.)
