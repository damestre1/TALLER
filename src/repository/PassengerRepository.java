package repository;

import model.Passenger;

import java.util.ArrayList;

public class PassengerRepository {

    private ArrayList<Passenger> passengers;

    public PassengerRepository() {

        passengers = new ArrayList<>();

    }

    public void addPassenger(
            Passenger passenger
    ) {

        passengers.add(passenger);

    }

    public Passenger findPassengerById(
            String id
    ) {

        for(Passenger passenger : passengers){

            if(
                    passenger.getId()
                            .equals(id)
            ){

                return passenger;

            }

        }

        return null;

    }

    public boolean existsPassenger(
            String id
    ){

        return findPassengerById(
                id
        ) != null;

    }

    public boolean existsPassport(
            String passport
    ){

        for(
                Passenger passenger
                : passengers
        ){

            if(
                    passenger
                            .getPassportNumber()
                            .equals(passport)
            ){

                return true;

            }

        }

        return false;

    }

    public ArrayList<Passenger>
    getPassengers(){

        return passengers;

    }

    public int totalPassengers(){

        return passengers.size();

    }

}