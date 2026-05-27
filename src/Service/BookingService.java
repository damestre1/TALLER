package service;
import model.Booking;
import model.Passenger;
import model.Route;
import repository.BookingRepository;
import java.util.ArrayList;

public class BookingService {
    private BookingRepository bookingRepository;
    private service.PassengerService passengerService;
    private service.RouteService routeService;

    public BookingService(BookingRepository bookingRepository, service.PassengerService passengerService, service.RouteService routeService) {
        this.bookingRepository = bookingRepository;
        this.passengerService = passengerService;
        this.routeService = routeService;
    }

    public Booking createBooking(String bookingCode, String passengerId, String routeCode, int seatsBooked, String bookingDate) throws service.FluxioBusException {
        if (bookingRepository.existsByCode(bookingCode)) {
            throw new service.FluxioBusException("Ya existe una reserva con ese código");
        }
        if (seatsBooked < 1 || seatsBooked > 5) {
            throw new service.FluxioBusException("No se pueden reservar más de 5 puestos por reserva");
        }

        Passenger passenger = passengerService.getPassenger(passengerId);
        Route route = routeService.getRoute(routeCode);

        if (!route.getStatus().equals("Programada")) {
            throw new service.FluxioBusException("No se puede reservar en una ruta que no esté en estado 'Programada'");
        }
        if (route.getAvailableSeats() < seatsBooked) {
            throw new service.FluxioBusException("No hay puestos disponibles para esta ruta");
        }

        Booking booking = new Booking(bookingCode, passenger, route, seatsBooked, bookingDate);
        route.setAvailableSeats(route.getAvailableSeats() - seatsBooked);
        bookingRepository.add(booking);
        return booking;
    }

    public void cancelBooking(String bookingCode) throws service.FluxioBusException {
        Booking booking = bookingRepository.findByCode(bookingCode);
        if (booking == null) {
            throw new service.FluxioBusException("No se encontró la reserva con ese código");
        }
        booking.setStatus("Cancelada");
        Route route = booking.getRoute();
        route.setAvailableSeats(route.getAvailableSeats() + booking.getSeatsBooked());
    }

    public Booking getBooking(String bookingCode) throws service.FluxioBusException {
        Booking booking = bookingRepository.findByCode(bookingCode);
        if (booking == null) {
            throw new service.FluxioBusException("No se encontró la reserva con ese código");
        }
        return booking;
    }

    public ArrayList<Booking> getBookingsByPassenger(String passengerId) throws service.FluxioBusException {
        passengerService.getPassenger(passengerId);
        return bookingRepository.findByPassengerId(passengerId);
    }

    public boolean hasAnyBooking() {
        return bookingRepository.hasAny();
    }
}
