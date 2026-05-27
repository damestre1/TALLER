package repository;
import model.Booking;
import java.util.ArrayList;

public class BookingRepository {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void add(Booking booking) {
        bookings.add(booking);
    }

    public Booking findByCode(String code) {
        for (Booking b : bookings) {
            if (b.getBookingCode().equals(code)) return b;
        }
        return null;
    }

    public boolean existsByCode(String code) {
        return findByCode(code) != null;
    }

    public ArrayList<Booking> findByPassengerId(String idNumber) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getPassenger().getIdNumber().equals(idNumber)) {
                result.add(b);
            }
        }
        return result;
    }

    public boolean hasAny() {
        return !bookings.isEmpty();
    }
}
