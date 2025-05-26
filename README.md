# Pricing Manager API

Este proyecto es una API REST para la gestión de precios, desarrollada con **Spring Boot**. En el marco de la postulación al cargo de desarrollador backend java.

## Descripción

La aplicación permite:

- Obtener el precio de un producto.

Incluye documentación interactiva con Swagger UI.

---

## Requisitos

- Java 17+
- Gradle
- Curl para probar endpoints (Opcional)

---

## Base de datos

La base de datos que utiliza este proyecto, es una base de datos en memoria H2. La cual se reinicia cada vez que se levanta la aplicación, borrando los datos que se hayan generado durante la ejecución del proyecto.

La base de datos se carga inicialmente con los siguientes datos:

### Tabla: `PRICE`

| BRAND_ID | START_DATE           | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|----------------------|----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00  | 2020-12-31 23:59:59  | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14 15:00:00  | 2020-06-14 18:30:00  | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15 00:00:00  | 2020-06-15 11:00:00  | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15 16:00:00  | 2020-12-31 23:59:59  | 4          | 35455      | 1        | 38.95 | EUR  |


---

## Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/hgajardofrancino/pricing

2. Construí el proyecto:

    ```bash
   ./gradlew clean build

3. Ejecutá la aplicación:

    ```bash
    ./gradlew bootRun

---
## Swagger

Para probar los endpoint se puede realizar mediante la interfaz de usuario de swagger, la cual se puede encontrar en la url: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

##  Endpoints

A coninuacion se muestran los CURLs asociados a las pruebas requeridas. Estos puedes ejecutarse directamente en consola o bien exportarlos a Postman.


###  - Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

```bash
    curl --location 'http://localhost:8080/api/v1/prices?productId=35455&brandId=1&date=2020-06-14T10%3A00%3A00'
```

### - Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

```bash
    curl --location 'http://localhost:8080/api/v1/prices?productId=35455&brandId=1&date=2020-06-14T16%3A00%3A00'
  
```

### - Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

```bash
    
    curl --location 'http://localhost:8080/api/v1/prices?productId=35455&brandId=1&date=2020-06-14T21%3A00%3A00'
  
```

### - Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

```bash
     
    curl --location 'http://localhost:8080/api/v1/prices?productId=35455&brandId=1&date=2020-06-15T10%3A00%3A00'
  
```

### - Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

```bash
     
    curl --location 'http://localhost:8080/api/v1/prices?productId=35455&brandId=1&date=2020-06-16T21%3A00%3A00'
  
```
## Contacto

Para consultas o sugerencias, favor escribirme a [hgajardofrancino@gmail.com](mailto:hgajardofrancinol@gmail.com).