package model;

public class NationalRoute extends Route {
    private boolean hasRestStops;

    public NationalRoute(String code, String originCity, String destinationCity, String departureDate, String departureTime, String arrivalTime, int totalCapacity, double basePrice, boolean hasRestStops) {
        super(code, originCity, destinationCity, departureDate, departureTime, arrivalTime, totalCapacity, basePrice);
        this.hasRestStops = hasRestStops;
    }

    @Override
    public double getFinalPrice() {
        return getBasePrice();
    }

    @Override
    public String toString() {
        return "[RUTA NACIONAL]\n" + super.toString() + "\n" +
                String.format("  Paradas       : %s", hasRestStops ? "Si" : "No");
    }
}
