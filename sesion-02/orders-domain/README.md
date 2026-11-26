# orders-domain · Sesión 1

Proyecto base del curso **Java y Microservicios con Kafka** de Intevex Academy.
Durante el Módulo 1 vas a construir aquí el modelo de dominio del sistema de pedidos.

## Requisitos

| Herramienta | Comando para verificar | Resultado esperado |
|---|---|---|
| JDK 21 | `java -version` | `openjdk version "21..."` |
| Maven 3.9+ | `mvn -v` | `Apache Maven 3.9...` y `Java version: 21...` |
| Git | `git --version` | `git version 2...` |
| IntelliJ IDEA | — | Community o Ultimate |

## Cómo ejecutarlo

```bash
mvn compile            # compila
mvn compile exec:java  # compila y ejecuta Main
mvn package            # genera target/orders-domain-1.0.0.jar
```

O desde IntelliJ: abre `Main.java` y haz clic en el ▶ verde junto a `main`.

## Qué hay en esta sesión

- `Main.java`: el punto de partida. Aquí crearemos productos y clientes.
- `retos/MiniRetos.java`: los mini retos de la clase (IGV y descuentos).
- `domain/`: aquí van `Product`, `Customer` y `CustomerType`, que construimos en vivo.

¿Terminaste antes? Abre **RETOS-PRO.md**.
