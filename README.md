# FluxioBus Colombia — Sistema de Gestión de Flota

Aplicación de consola desarrollada en Java para gestionar las operaciones de transporte terrestre de la empresa **FluxioBus Colombia**. Permite registrar pasajeros, administrar rutas nacionales e internacionales, procesar reservas de tiquetes y generar reportes de operación.

Proyecto académico desarrollado para la asignatura **Programación de Computadores II (SS300)** de la Universidad Popular del Cesar.

---

## Tabla de contenidos

- [Funcionalidades](#funcionalidades)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Conceptos aplicados](#conceptos-aplicados)
- [Requisitos](#requisitos)
- [Ejecución](#ejecución)
- [Reglas de negocio](#reglas-de-negocio)
- [Validaciones de entrada](#validaciones-de-entrada)

---

## Funcionalidades

**Gestión de pasajeros**
- Registrar un nuevo pasajero con todos sus datos personales
- Consultar un pasajero por número de cédula

**Gestión de rutas**
- Registrar rutas nacionales (dentro de Colombia)
- Registrar rutas internacionales (Venezuela, Ecuador, Perú)
- Consultar una ruta por su código
- Cálculo automático de la duración estimada del recorrido a partir de la hora de salida y llegada

**Gestión de reservas**
- Crear una reserva vinculando un pasajero y una ruta
- Cancelar una reserva y devolver los puestos automáticamente a la ruta
- Consultar una reserva por su código

**Reportes y estadísticas**
- Listar todos los pasajeros registrados con sus datos completos
- Consultar todas las reservas de un pasajero, con su nombre, cédula y conteo total de reservas

---

## Estructura del proyecto

```
src/
├── model/                  # Capa de dominio
│   ├── Route.java              # Clase padre abstracta de rutas
│   ├── NationalRoute.java      # Hereda de Route — rutas dentro de Colombia
│   ├── InternationalRoute.java # Hereda de Route — rutas al exterior
│   ├── Passenger.java          # Datos del pasajero
│   └── Booking.java            # Reserva: relaciona Passenger con Route
│
├── repository/             # Capa de datos (almacén en memoria)
│   ├── PassengerRepository.java
│   ├── RouteRepository.java
│   └── BookingRepository.java
│
├── service/                # Capa de servicios (lógica de negocio y validaciones)
│   ├── PassengerService.java
│   ├── RouteService.java
│   ├── BookingService.java
│   └── FluxioBusException.java # Excepción personalizada del dominio
│
└── presentation/           # Capa de presentación (interfaz de consola)
    ├── Main.java               # Menú principal y flujo de la aplicación
    └── InputValidator.java     # Validaciones de entrada reutilizables
```

---

## Conceptos aplicados

| Concepto | Dónde se aplica |
|---|---|
| **Herencia** | `NationalRoute` e `InternationalRoute` heredan de `Route` |
| **Polimorfismo** | `getFinalPrice()` es abstracto en `Route` y cada subclase lo implementa de forma distinta |
| **Encapsulamiento** | Todos los atributos son `private`/`protected`, expuestos solo con getters y setters |
| **Programación en capas** | Separación estricta entre `model`, `repository`, `service` y `presentation` |
| **ArrayList** | Cada repositorio usa un `ArrayList` como almacén en memoria |
| **Relaciones entre clases** | `Booking` contiene referencias a `Passenger` y `Route` |
| **Excepciones** | `FluxioBusException` personalizada; bloques `try-catch` en toda la capa de presentación |
| **Reutilización de código** | `InputValidator` centraliza todas las validaciones de entrada, evitando duplicación |

### Cálculo del precio final

- **Ruta nacional:** `precio final = precio base`
- **Ruta internacional:** `precio final = precio base + cargo internacional`

### Cálculo de la duración estimada

La duración se calcula automáticamente en `Route.getEstimatedDuration()` a partir de la hora de salida y llegada ingresadas. Soporta recorridos que cruzan medianoche.

---

## Requisitos

- Java 8 o superior
- No requiere librerías externas ni frameworks

---

## Ejecución

**1. Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/fluxiobus-colombia.git
cd fluxiobus-colombia
```

**2. Compilar**
```bash
javac -d out -sourcepath src src/presentation/Main.java
```

**3. Ejecutar**
```bash
java -cp out presentation.Main
```

---

## Reglas de negocio

**Pasajeros**
- La cédula y el número de pasaporte deben ser únicos
- El correo electrónico debe contener el símbolo `@`
- La edad debe ser mayor o igual a 0

**Rutas**
- El código de ruta debe ser único
- La capacidad máxima es de 32 puestos
- El precio base debe ser mayor a cero
- Los puestos disponibles nunca pueden ser negativos
- El estado puede ser: `Programada`, `En Camino`, `Completada` o `Cancelada`

**Reservas**
- Solo se puede reservar en rutas con estado `Programada`
- Mínimo 1 puesto y máximo 5 por reserva
- El código de reserva debe ser único
- Al cancelar, los puestos se devuelven automáticamente a la ruta

---

## Validaciones de entrada

Todas las validaciones de entrada están centralizadas en `InputValidator` y se reutilizan en toda la aplicación:

| Método | Acepta |
|---|---|
| `readNumericString()` | Solo dígitos — cédula, teléfono, pasaporte, códigos |
| `readText()` | Solo letras — nombres, apellidos, nacionalidad, ciudades |
| `readDate()` | Formato `DD/MM/YYYY` con valores numéricos válidos |
| `readTime()` | Formato `HH:MM` con valores numéricos válidos |
| `readPositiveInt()` | Entero mayor o igual a cero |
| `readPositiveDouble()` | Decimal mayor a cero |

Si el usuario ingresa un valor inválido, el sistema muestra un mensaje de error y vuelve a pedir el dato sin interrumpir la ejecución.
