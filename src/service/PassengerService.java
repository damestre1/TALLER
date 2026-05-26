package service;

import exception.DuplicatePassengerException;
import exception.DuplicatePassportException;
import exception.InvalidEmailException;
import exception.PassengerNotFoundException;

import model.Passenger;
import repository.PassengerRepository;
import util.ValidationUtil;

public class PassengerService {

    private PassengerRepository repository;

    public PassengerService(
            PassengerRepository repository
    ) {

        this.repository = repository;

    }

    public void registerPassenger(
            Passenger passenger
    ) throws Exception {

        if(
                repository.existsPassenger(
                        passenger.getId()
                )
        ){

            throw new DuplicatePassengerException(
                    "Ya existe un pasajero con esa cédula"
            );

        }

        if(
                repository.existsPassport(
                        passenger.getPassportNumber()
                )
        ){

            throw new DuplicatePassportException(
                    "Ya existe un pasajero con ese pasaporte"
            );

        }

        if(
                !ValidationUtil.validateEmail(
                        passenger.getEmail()
                )
        ){

            throw new InvalidEmailException(
                    "El email debe contener el símbolo @"
            );

        }

        if(
                passenger.getAge() < 0
        ){

            throw new Exception(
                    "La edad no puede ser negativa"
            );

        }

        repository.addPassenger(
                passenger
        );

    }

    public Passenger findPassengerById(
            String id
    ) throws Exception {

        Passenger passenger =
                repository.findPassengerById(
                        id
                );

        if(
                passenger == null
        ){

            throw new PassengerNotFoundException(
                    "No se encontró el pasajero con esa cédula"
            );

        }

        return passenger;

    }

    public int totalPassengers(){

        return repository.totalPassengers();

    }

}