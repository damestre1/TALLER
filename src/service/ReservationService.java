package service;

import exception.NoSeatsAvailableException;
import exception.ReservationNotFoundException;

import model.Passenger;
import model.Reservation;
import model.Route;

import repository.ReservationRepository;

public class ReservationService {

    private ReservationRepository repository;
    private PassengerService passengerService;
    private RouteService routeService;

    public ReservationService(
            ReservationRepository repository,
            PassengerService passengerService,
            RouteService routeService
    ) {

        this.repository = repository;
        this.passengerService = passengerService;
        this.routeService = routeService;

    }

    public void createReservation(
            Reservation reservation
    ) throws Exception {

        if(
                repository.existsReservation(
                        reservation.getReservationCode()
                )
        ){

            throw new Exception(
                    "Ya existe una reserva con ese código"
            );

        }

        Passenger passenger =
                passengerService.findPassengerById(

                        reservation
                                .getPassenger()
                                .getId()

                );

        Route route =
                routeService.findRoute(

                        reservation
                                .getRoute()
                                .getRouteCode()

                );

        if(
                !route.getStatus()
                        .equalsIgnoreCase(
                                "Programada"
                        )
        ){

            throw new Exception(
                    "La ruta no está disponible"
            );

        }

        if(
                reservation
                        .getReservedSeats() < 1
        ){

            throw new Exception(
                    "Debe reservar mínimo 1 puesto"
            );

        }

        if(
                reservation
                        .getReservedSeats() > 5
        ){

            throw new Exception(
                    "No se pueden reservar más de 5 puestos por reserva"
            );

        }

        if(
                route.getAvailableSeats()
                        <
                        reservation.getReservedSeats()
        ){

            throw new NoSeatsAvailableException(
                    "No hay puestos disponibles para esta ruta"
            );

        }

        route.setAvailableSeats(

                route.getAvailableSeats()

                        -

                        reservation.getReservedSeats()

        );

        repository.addReservation(
                reservation
        );

    }

    public void cancelReservation(
            String reservationCode
    ) throws Exception {

        Reservation reservation =
                repository.findReservation(
                        reservationCode
                );

        if(
                reservation == null
        ){

            throw new ReservationNotFoundException(
                    "No se encontró la reserva con ese código"
            );

        }

        Route route =
                reservation.getRoute();

        route.setAvailableSeats(

                route.getAvailableSeats()

                        +

                        reservation.getReservedSeats()

        );

        reservation.setStatus(
                "Cancelada"
        );

    }

    public Reservation findReservation(
            String reservationCode
    ) throws Exception {

        Reservation reservation =
                repository.findReservation(
                        reservationCode
                );

        if(
                reservation == null
        ){

            throw new ReservationNotFoundException(
                    "No se encontró la reserva con ese código"
            );

        }

        return reservation;

    }

}