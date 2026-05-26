
import model.Passenger;

import repository.PassengerRepository;
import repository.ReservationRepository;
import repository.RouteRepository;

import service.PassengerService;
import service.ReservationService;
import service.RouteService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        PassengerRepository
                passengerRepository =
                new PassengerRepository();

        RouteRepository
                routeRepository =
                new RouteRepository();

        ReservationRepository
                reservationRepository =
                new ReservationRepository();

        PassengerService
                passengerService =
                new PassengerService(
                        passengerRepository
                );

        RouteService
                routeService =
                new RouteService(
                        routeRepository
                );

        ReservationService
                reservationService =
                new ReservationService(
                        reservationRepository,
                        passengerService,
                        routeService
                );

        int option;

        do{

            System.out.println(
                    "\n===== FLUXIOBUS ====="
            );

            System.out.println(
                    "1. Registrar pasajero"
            );

            System.out.println(
                    "2. Total pasajeros"
            );

            System.out.println(
                    "3. Salir"
            );

            System.out.print(
                    "Seleccione: "
            );

            option =
                    scanner.nextInt();

            scanner.nextLine();

            try{

                switch(option){

                    case 1:

                        System.out.print(
                                "Cedula: "
                        );

                        String id =
                                scanner.nextLine();

                        System.out.print(
                                "Nombre: "
                        );

                        String firstName =
                                scanner.nextLine();

                        System.out.print(
                                "Apellido: "
                        );

                        String lastName =
                                scanner.nextLine();

                        System.out.print(
                                "Edad: "
                        );

                        int age =
                                scanner.nextInt();

                        scanner.nextLine();

                        System.out.print(
                                "Correo: "
                        );

                        String email =
                                scanner.nextLine();

                        System.out.print(
                                "Telefono: "
                        );

                        String phone =
                                scanner.nextLine();

                        System.out.print(
                                "Pasaporte: "
                        );

                        String passport =
                                scanner.nextLine();

                        System.out.print(
                                "Nacionalidad: "
                        );

                        String nationality =
                                scanner.nextLine();

                        Passenger passenger =
                                new Passenger(

                                        id,
                                        firstName,
                                        lastName,
                                        age,
                                        email,
                                        phone,
                                        passport,
                                        nationality

                                );

                        passengerService
                                .registerPassenger(
                                        passenger
                                );

                        System.out.println(
                                "Pasajero registrado"
                        );

                        break;

                    case 2:

                        System.out.println(

                                "Total pasajeros: "

                                        +

                                        passengerService
                                                .totalPassengers()

                        );

                        break;

                    case 3:

                        System.out.println(
                                "Saliendo..."
                        );

                        break;

                    default:

                        System.out.println(
                                "Opcion invalida"
                        );

                }

            }catch(Exception e){

                System.out.println(

                        e.getMessage()

                );

            }

        }while(option != 3);

    }

}