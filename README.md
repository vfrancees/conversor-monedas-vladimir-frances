# Conversor de Monedas — Vladimir Francés

Proyecto del desafío: **consumo de API, JSON y filtrado de monedas** en Java.

## App usada
- Java 17
- Maven
- Gson (parseo JSON)
- `HttpClient` (Java 11+)

API usada: `exchangerate.host` (sin API key).

## Como ejecutar
```bash
# 1) Compilar y empaquetar
mvn -q -DskipTests package

# 2) Ejecutar (jar sombreado)
java -jar target/conversor-monedas-1.0.0-shaded.jar
```

## Como usarlo
1. Ingresa la **moneda base** (ej: `USD`, `EUR`, `DOP`).
2. Ingresa el **monto**.
3. Lista las **monedas destino** separadas por coma (o deja vacío para sugeridas: `USD,EUR,DOP,MXN,COP,ARS,BRL`).

**Ejemplo:**
```
Moneda base: USD
Monto: 100
Monedas destino: DOP,EUR,MXN
```

Salida típica:
```
Resultados:
  DOP: 5898.1234
  EUR:  91.2345
  MXN: 1701.5678
```

> Si la API está temporalmente inalcanzable, verás un mensaje de error de red.

##  Pruebas
Ejecuta:
```bash
mvn test
```

## Estructura
```
conversor-monedas-vladimir-frances/
├─ pom.xml
├─ src
│  ├─ main/java/com/vladimirfrances/currency
│  │  ├─ Main.java
│  │  ├─ ConsoleUI.java
│  │  ├─ CurrencyConverter.java
│  │  ├─ ExchangeRateClient.java
│  └─ test/java/com/vladimirfrances/currency
└─ README.md
```

## Trello (flujo agil sugerido)
- **Listos para iniciar**: crear proyecto Maven, configurar dependencias, README, `.gitignore`.
- **En desarrollo**: cliente HTTP, parseo JSON, UI de consola, validaciones.
- **Pausado**: tareas con bloqueo externo (p. ej. sin internet).
- **Concluido**: empaquetado, pruebas, commit/push a GitHub, README final con capturas y demo.

Checklist (copiar en una tarjeta):
- [ ] Estructura Maven creada
- [ ] Dependencias Gson / Shade
- [ ] Llamada a API `exchangerate.host`
- [ ] Filtro de monedas y conversión
- [ ] Manejo de errores y validaciones
- [ ] README con uso y demo
- [ ] Publicado en GitHub y compartido en LinkedIn

## Licencia
MIT © 2025 Vladimir Frances
