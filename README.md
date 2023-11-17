# Proyecto de Servicio de Precios

El proyecto consiste en un servicio de gestión de precios que ofrece funcionalidades para obtener precios basados en fechas, productos y marcas. A continuación, se detalla la estructura del proyecto y su funcionamiento.

## Estructura del Proyecto

El proyecto está estructurado en diferentes capas:

### Capa de Controlador (Controller)

La capa `controller` contiene el controlador principal que maneja las solicitudes REST.

- `PriceController (REST Adapter)`: Controlador que gestiona las solicitudes relacionadas con los precios. Ofrece endpoints para obtener todos los precios y obtener un precio específico por fecha de aplicación, ID de producto y ID de marca.

### Capa de Repositorio (Repository)

La capa `repository` contiene la interfaz del repositorio JPA y consultas personalizadas.

- `PriceRepository  (JPA Adapter)`: Interfaz que extiende JpaRepository y define consultas personalizadas para acceder a los datos de precios.

### Capa de Servicio (Service)

La capa `service` contiene la lógica de negocio del servicio de precios.

- `PriceAdapterService`: Implementación del servicio que interactúa con el repositorio para obtener precios y seleccionar el precio correcto en función de la fecha de aplicación, el producto y la marca.

### Clases de Dominio (Model)

Las clases `model` representan las entidades principales del dominio.

- `PriceRequest (Request Object)`: Modelo para la solicitud de precios.
- `PriceResponse (Response Object)`: Modelo para la respuesta de precios.
- `Prices  (Domain Entity/Model)`: Modelo que representa la estructura de datos de precios.

### Clase Principal de Aplicación

- `PriceServiceApp`: Clase principal que inicia la aplicación Spring Boot.

## Cómo Usar

### Prerrequisitos

- JDK 17 
- Maven 4.0.0+ (para compilación y gestión de dependencias)

### Instalación

1. Clonar el repositorio:

    ```bash
    https://github.com/momah/PricingService.git
    cd PricingService
    ```

2. Compilar el proyecto:

    ```bash
    mvn clean package
    ```

3. Ejecutar la aplicación:

    ```bash
    mvn spring-boot:run
    ```

La aplicación se iniciará en `http://localhost:8080`.

### Endpoints

- `GET /api/allPrices`: Obtiene todos los precios disponibles.
- `GET /api/price?applicationDate={date}&productId={productId}&brandId={brandId}`: Obtiene un precio específico según la fecha de aplicación, el ID de producto y el ID de marca.


#### Ejemplos de Uso

```bash
curl -X GET http://localhost:8080/api
```
```bash
curl -X GET http://localhost:8080/api/allPrices
```
```bash
curl -X GET http://localhost:8080/api/price?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1
```


