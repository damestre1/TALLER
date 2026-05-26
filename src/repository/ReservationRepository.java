package repository;

import model.Reservation;

import java.util.ArrayList;

public class ReservationRepository {

    private ArrayList<Reservation> reservations;

    public ReservationRepository() {

        reservations = new ArrayList<>();

    }

    public void addReservation(
            Reservation reservation
    ) {

        reservations.add(
                reservation
        );

    }

    public Reservation findReservation(
            String reservationCode
    ) {

        for(
                Reservation reservation
                : reservations
        ){

            if(
                    reservation
                            .getReservationCode()
                            .equals(
                                    reservationCode
                            )
            ){

                return reservation;

            }

        }

        return null;

    }

    public boolean existsReservation(
            String reservationCode
    ){

        return findReservation(
                reservationCode
        ) != null;

    }

    public ArrayList<Reservation>
    getReservations(){

        return reservations;

    }

}