package model;

public class Reservation {

    private String reservationCode;
    private Passenger passenger;
    private Route route;
    private int reservedSeats;
    private String reservationDate;
    private double totalPrice;
    private String status;

    public Reservation() {
    }

    public Reservation(
            String reservationCode,
            Passenger passenger,
            Route route,
            int reservedSeats,
            String reservationDate,
            String status
    ) {

        this.reservationCode = reservationCode;
        this.passenger = passenger;
        this.route = route;
        this.reservedSeats = reservedSeats;
        this.reservationDate = reservationDate;
        this.status = status;

        calculateTotalPrice();

    }

    public void calculateTotalPrice() {

        totalPrice =
                route.calculateFinalPrice()
                        * reservedSeats;

    }

    public String getReservationCode() {

        return reservationCode;

    }

    public void setReservationCode(
            String reservationCode
    ) {

        this.reservationCode = reservationCode;

    }

    public Passenger getPassenger() {

        return passenger;

    }

    public void setPassenger(
            Passenger passenger
    ) {

        this.passenger = passenger;

    }

    public Route getRoute() {

        return route;

    }

    public void setRoute(
            Route route
    ) {

        this.route = route;

    }

    public int getReservedSeats() {

        return reservedSeats;

    }

    public void setReservedSeats(
            int reservedSeats
    ) {

        this.reservedSeats = reservedSeats;

    }

    public String getReservationDate() {

        return reservationDate;

    }

    public void setReservationDate(
            String reservationDate
    ) {

        this.reservationDate = reservationDate;

    }

    public double getTotalPrice() {

        return totalPrice;

    }

    public String getStatus() {

        return status;

    }

    public void setStatus(
            String status
    ) {

        this.status = status;

    }

    @Override
    public String toString() {

        return
                "\nCodigo reserva: "
                        + reservationCode +

                        "\nPasajero: "
                        + passenger.getFirstName()
                        + " "
                        + passenger.getLastName()

                        +

                        "\nRuta: "
                        + route.getRouteCode()

                        +

                        "\nPuestos: "
                        + reservedSeats

                        +

                        "\nFecha reserva: "
                        + reservationDate

                        +

                        "\nPrecio total: "
                        + totalPrice

                        +

                        "\nEstado: "
                        + status;

    }

}