package model;

public class Booking {
    private String bookingCode;
    private Passenger passenger;
    private Route route;
    private int seatsBooked;
    private String bookingDate;
    private double totalPrice;
    private String status;

    public Booking(String bookingCode, Passenger passenger, Route route, int seatsBooked, String bookingDate) {
        this.bookingCode = bookingCode;
        this.passenger = passenger;
        this.route = route;
        this.seatsBooked = seatsBooked;
        this.bookingDate = bookingDate;
        this.totalPrice = route.getFinalPrice() * seatsBooked;
        this.status = "Confirmada";
    }

    public String getBookingCode() { return bookingCode; }
    public Passenger getPassenger() { return passenger; }
    public Route getRoute() { return route; }
    public int getSeatsBooked() { return seatsBooked; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format(
                "  Codigo        : %s\n" +
                        "  Pasajero      : %s %s (Ced: %s)\n" +
                        "  Ruta          : %s (%s -> %s)\n" +
                        "  Puestos       : %d\n" +
                        "  Precio total  : $%.0f\n" +
                        "  Fecha reserva : %s\n" +
                        "  Estado        : %s",
                bookingCode,
                passenger.getFirstName(), passenger.getLastName(), passenger.getIdNumber(),
                route.getCode(), route.getOriginCity(), route.getDestinationCity(),
                seatsBooked, totalPrice, bookingDate, status
        );
    }
}
