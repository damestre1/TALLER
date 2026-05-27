package presentation;
import model.Booking;
import model.Passenger;
import repository.BookingRepository;
import repository.PassengerRepository;
import repository.RouteRepository;
import service.BookingService;
import service.FluxioBusException;
import service.PassengerService;
import service.RouteService;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static InputValidator input = new InputValidator(scanner);

    private static PassengerRepository passengerRepository = new PassengerRepository();
    private static RouteRepository     routeRepository     = new RouteRepository();
    private static BookingRepository   bookingRepository   = new BookingRepository();

    private static PassengerService passengerService = new PassengerService(passengerRepository);
    private static RouteService     routeService     = new RouteService(routeRepository);
    private static BookingService   bookingService   = new BookingService(bookingRepository, passengerService, routeService);

    //impresión
    private static void printSeparator() {
        System.out.println("  ─────────────────────────────────────────");
    }

    private static void printHeader(String title) {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("  " + title);
        System.out.println("=========================================");
    }

    private static void printSuccess(String msg) {
        System.out.println("  [OK] " + msg);
    }

    private static void printError(String msg) {
        System.out.println("  [!] " + msg);
    }

    //menú
    public static void main(String[] args) {
        printHeader("SISTEMA FLUXIOBUS COLOMBIA");

        boolean running = true;
        while (running) {
            System.out.println("\n  --- MENU PRINCIPAL ---");
            System.out.println("  1. Gestion de Pasajeros");
            System.out.println("  2. Gestion de Rutas");
            System.out.println("  3. Gestion de Reservas");
            System.out.println("  4. Reportes y Estadisticas");
            System.out.println("  0. Salir");
            int option = input.readMenuOption("  Seleccione: ");
            switch (option) {
                case 1: menuPassengers(); break;
                case 2: menuRoutes();     break;
                case 3: menuBookings();   break;
                case 4: menuReports();    break;
                case 0:
                    System.out.println("\n  Hasta luego.");
                    running = false;
                    break;
                default:
                    printError("Opcion no valida.");
            }
        }
        scanner.close();
    }

    //1.
    private static void menuPassengers() {
        printHeader("GESTION DE PASAJEROS");
        System.out.println("  1. Registrar pasajero");
        System.out.println("  2. Consultar pasajero por cedula");
        int option = input.readMenuOption("  Seleccione: ");
        switch (option) {
            case 1: registerPassenger(); break;
            case 2: findPassenger();     break;
            default: printError("Opcion no valida.");
        }
    }

    private static void registerPassenger() {
        printHeader("REGISTRO DE PASAJERO");
        try {
            String id          = input.readNumericString("  Cedula           : ");
            String firstName   = input.readText(         "  Nombres          : ");
            String lastName    = input.readText(         "  Apellidos        : ");
            int    age         = input.readPositiveInt(  "  Edad             : ");
            System.out.print(                            "  Email            : ");
            String email       = scanner.nextLine().trim();
            String phone       = input.readNumericString("  Telefono         : ");
            String passport    = input.readNumericString("  Num. de pasaporte: ");
            String nationality = input.readText(         "  Nacionalidad     : ");

            passengerService.registerPassenger(id, firstName, lastName, age,
                    email, phone, passport, nationality);
            printSuccess("Pasajero registrado exitosamente.");
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        } catch (Exception e) {
            printError("Error inesperado: " + e.getMessage());
        }
    }

    private static void findPassenger() {
        printHeader("CONSULTAR PASAJERO");
        try {
            String id = input.readNumericString("  Cedula: ");
            printSeparator();
            System.out.println(passengerService.getPassenger(id));
            printSeparator();
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        }
    }

    //2.
    private static void menuRoutes() {
        printHeader("GESTION DE RUTAS");
        System.out.println("  1. Registrar ruta nacional");
        System.out.println("  2. Registrar ruta internacional");
        System.out.println("  3. Consultar ruta por codigo");
        int option = input.readMenuOption("  Seleccione: ");
        switch (option) {
            case 1: registerNationalRoute();       break;
            case 2: registerInternationalRoute();  break;
            case 3: findRoute();                   break;
            default: printError("Opcion no valida.");
        }
    }

    private static void registerNationalRoute() {
        printHeader("REGISTRAR RUTA NACIONAL");
        try {
            String code        = input.readNumericString("  Codigo de ruta      : ");
            String origin      = input.readText(         "  Ciudad de origen    : ");
            String destination = input.readText(         "  Ciudad de destino   : ");
            String date        = input.readDate(         "  Fecha salida (DD/MM/YYYY): ");
            String depTime     = input.readTime(         "  Hora de salida (HH:MM)  : ");
            String arrTime     = input.readTime(         "  Hora de llegada (HH:MM) : ");
            int    capacity    = input.readPositiveInt(  "  Capacidad total (max 32): ");
            double basePrice   = input.readPositiveDouble("  Precio base             : ");
            System.out.print(                            "  Paradas de descanso (s/n): ");
            boolean hasStops   = scanner.nextLine().trim().equalsIgnoreCase("s");

            routeService.registerNationalRoute(code, origin, destination, date,
                    depTime, arrTime, capacity, basePrice, hasStops);
            printSuccess("Ruta nacional registrada. Precio final: $" + (long) basePrice);
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        } catch (Exception e) {
            printError("Error inesperado: " + e.getMessage());
        }
    }

    private static void registerInternationalRoute() {
        printHeader("REGISTRAR RUTA INTERNACIONAL");
        try {
            String code        = input.readNumericString("  Codigo de ruta           : ");
            String origin      = input.readText(         "  Ciudad de origen         : ");
            String destination = input.readText(         "  Ciudad de destino        : ");
            String date        = input.readDate(         "  Fecha salida (DD/MM/YYYY): ");
            String depTime     = input.readTime(         "  Hora de salida (HH:MM)  : ");
            String arrTime     = input.readTime(         "  Hora de llegada (HH:MM) : ");
            int    capacity    = input.readPositiveInt(  "  Capacidad total (max 32) : ");
            double basePrice   = input.readPositiveDouble("  Precio base              : ");
            String country     = input.readText(         "  Pais de destino          : ");
            System.out.print(                            "  Requiere pasaporte (s/n) : ");
            boolean requiresPassport = scanner.nextLine().trim().equalsIgnoreCase("s");
            double fee         = input.readPositiveDouble("  Cargo internacional      : ");

            routeService.registerInternationalRoute(code, origin, destination, date,
                    depTime, arrTime, capacity, basePrice, country, requiresPassport, fee);
            printSuccess("Ruta internacional registrada. Precio final: $" + (long)(basePrice + fee));
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        } catch (Exception e) {
            printError("Error inesperado: " + e.getMessage());
        }
    }

    private static void findRoute() {
        printHeader("CONSULTAR RUTA");
        try {
            String code = input.readNumericString("  Codigo de ruta: ");
            printSeparator();
            System.out.println(routeService.getRoute(code));
            printSeparator();
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        }
    }

    //3.
    private static void menuBookings() {
        printHeader("GESTION DE RESERVAS");
        System.out.println("  1. Crear reserva");
        System.out.println("  2. Cancelar reserva");
        System.out.println("  3. Consultar reserva por codigo");
        int option = input.readMenuOption("  Seleccione: ");
        switch (option) {
            case 1: createBooking(); break;
            case 2: cancelBooking(); break;
            case 3: findBooking();   break;
            default: printError("Opcion no valida.");
        }
    }

    private static void createBooking() {
        printHeader("CREAR RESERVA");
        try {
            String bookingCode  = input.readNumericString("  Codigo de reserva (ej: 001): ");
            String passengerId  = input.readNumericString("  Cedula del pasajero         : ");
            String routeCode    = input.readNumericString("  Codigo de ruta              : ");
            int    seats        = input.readPositiveInt(  "  Cantidad de puestos (1-5)   : ");
            String bookingDate  = input.readDate(         "  Fecha de reserva (DD/MM/YYYY): ");

            Booking booking = bookingService.createBooking(bookingCode, passengerId,
                    routeCode, seats, bookingDate);
            printSuccess("Reserva creada exitosamente.");
            printSeparator();
            System.out.println(booking);
            printSeparator();
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        } catch (Exception e) {
            printError("Error inesperado: " + e.getMessage());
        }
    }

    private static void cancelBooking() {
        printHeader("CANCELAR RESERVA");
        if (!bookingService.hasAnyBooking()) {
            printError("No hay reservas registradas en el sistema.");
            return;
        }
        try {
            String code = input.readNumericString("  Codigo de reserva: ");
            bookingService.cancelBooking(code);
            printSuccess("Reserva cancelada. Puestos devueltos a la ruta.");
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        }
    }

    private static void findBooking() {
        printHeader("CONSULTAR RESERVA");
        if (!bookingService.hasAnyBooking()) {
            printError("No hay reservas registradas en el sistema.");
            return;
        }
        try {
            String code = input.readNumericString("  Codigo de reserva: ");
            printSeparator();
            System.out.println(bookingService.getBooking(code));
            printSeparator();
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        }
    }

    //4.
    private static void menuReports() {
        printHeader("REPORTES Y ESTADISTICAS");
        System.out.println("  1. Total de pasajeros registrados");
        System.out.println("  2. Reservas por pasajero");
        int option = input.readMenuOption("  Seleccione: ");
        switch (option) {
            case 1: reportAllPassengers();        break;
            case 2: reportBookingsByPassenger();  break;
            default: printError("Opcion no valida.");
        }
    }

    private static void reportAllPassengers() {
        printHeader("REPORTE DE PASAJEROS");
        ArrayList<Passenger> passengers = passengerService.getAllPassengers();
        System.out.println("  Total registrados: " + passengers.size());
        if (passengers.isEmpty()) {
            printError("No hay pasajeros registrados.");
            return;
        }
        for (int i = 0; i < passengers.size(); i++) {
            printSeparator();
            System.out.println("  Pasajero #" + (i + 1));
            System.out.println(passengers.get(i));
        }
        printSeparator();
    }

    private static void reportBookingsByPassenger() {
        printHeader("RESERVAS POR PASAJERO");
        ArrayList<Passenger> passengers = passengerService.getAllPassengers();
        if (passengers.isEmpty()) {
            printError("No hay pasajeros registrados en el sistema.");
            return;
        }
        try {
            String id = input.readNumericString("  Cedula del pasajero: ");
            Passenger p = passengerService.getPassenger(id);
            ArrayList<Booking> bookings = bookingService.getBookingsByPassenger(id);

            printSeparator();
            System.out.printf("  Pasajero  : %s %s%n", p.getFirstName(), p.getLastName());
            System.out.printf("  Cedula    : %s%n", p.getIdNumber());
            System.out.printf("  Reservas  : %d%n", bookings.size());
            printSeparator();

            if (bookings.isEmpty()) {
                System.out.println("  Este pasajero no tiene reservas.");
            } else {
                for (int i = 0; i < bookings.size(); i++) {
                    System.out.println("  Reserva #" + (i + 1));
                    System.out.println(bookings.get(i));
                    if (i < bookings.size() - 1) printSeparator();
                }
            }
            printSeparator();
        } catch (FluxioBusException e) {
            printError(e.getMessage());
        }
    }
}