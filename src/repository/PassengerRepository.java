package repository;
import model.Passenger;
import java.util.ArrayList;

public class PassengerRepository {
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public void add(Passenger passenger) {
        passengers.add(passenger);
    }

    public Passenger findById(String idNumber) {
        for (Passenger p : passengers) {
            if (p.getIdNumber().equals(idNumber)) return p;
        }
        return null;
    }

    public boolean existsById(String idNumber) {
        return findById(idNumber) != null;
    }

    public boolean existsByPassport(String passportNumber) {
        for (Passenger p : passengers) {
            if (p.getPassportNumber().equals(passportNumber)) return true;
        }
        return false;
    }

    public int count() {
        return passengers.size();
    }

    public ArrayList<Passenger> getAll() {
        return passengers;
    }
}
