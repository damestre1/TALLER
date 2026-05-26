package model;

public class NationalRoute extends Route {

    private double estimatedHours;
    private boolean hasRestStops;

    public NationalRoute(
            String routeCode,
            String originCity,
            String destinationCity,
            String departureDate,
            String departureTime,
            String arrivalTime,
            int totalSeats,
            double basePrice,
            String status,
            double estimatedHours,
            boolean hasRestStops
    ) {

        super(
                routeCode,
                originCity,
                destinationCity,
                departureDate,
                departureTime,
                arrivalTime,
                totalSeats,
                basePrice,
                status
        );

        this.estimatedHours = estimatedHours;
        this.hasRestStops = hasRestStops;

    }

    @Override
    public double calculateFinalPrice() {

        return getBasePrice();

    }

    public double getEstimatedHours() {

        return estimatedHours;

    }

    public void setEstimatedHours(
            double estimatedHours
    ) {

        this.estimatedHours = estimatedHours;

    }

    public boolean isHasRestStops() {

        return hasRestStops;

    }

    public void setHasRestStops(
            boolean hasRestStops
    ) {

        this.hasRestStops = hasRestStops;

    }

    @Override
    public String toString() {

        return super.toString()
                + "\nHoras estimadas: "
                + estimatedHours
                + "\nParadas descanso: "
                + hasRestStops
                + "\nPrecio final: "
                + calculateFinalPrice();

    }

}