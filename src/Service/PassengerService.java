package service;
import model.Passenger;
import repository.PassengerRepository;

public class PassengerService {
    private PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public void registerPassenger(String idNumber, String firstName, String lastName,
                                  int age, String email, String phoneNumber,
                                  String passportNumber, String nationality) throws service.FluxioBusException {
        if (passengerRepository.existsById(idNumber)) {
            throw new service.FluxioBusException("Ya existe un pasajero con esa cédula");
        }
        if (passengerRepository.existsByPassport(passportNumber)) {
            throw new service.FluxioBusException("Ya existe un pasajero con ese número de pasaporte");
        }
        if (!email.contains("@")) {
            throw new service.FluxioBusException("El email debe contener el símbolo @");
        }
        if (age < 0) {
            throw new service.FluxioBusException("La edad no puede ser negativa");
        }

        Passenger passenger = new Passenger(idNumber, firstName, lastName, age,
                email, phoneNumber, passportNumber, nationality);
        passengerRepository.add(passenger);
    }

    public Passenger getPassenger(String idNumber) throws service.FluxioBusException {
        Passenger p = passengerRepository.findById(idNumber);
        if (p == null) throw new service.FluxioBusException("No se encontró el pasajero con esa cédula");
        return p;
    }

    public int getTotalPassengers() {
        return passengerRepository.count();
    }

    public java.util.ArrayList<model.Passenger> getAllPassengers() {
        return passengerRepository.getAll();
    }
}
