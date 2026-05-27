package model;

public class InternationalRoute extends Route {
    private String destinationCountry;
    private boolean requiresPassport;
    private double internationalFee;

    public InternationalRoute(String code, String originCity, String destinationCity, String departureDate, String departureTime, String arrivalTime, int totalCapacity, double basePrice, String destinationCountry, boolean requiresPassport, double internationalFee) {
        super(code, originCity, destinationCity, departureDate, departureTime, arrivalTime, totalCapacity, basePrice);
        this.destinationCountry = destinationCountry;
        this.requiresPassport = requiresPassport;
        this.internationalFee = internationalFee;
    }

    @Override
    public double getFinalPrice() {
        return getBasePrice() + internationalFee;
    }

    @Override
    public String toString() {
        return "[RUTA INTERNACIONAL]\n" + super.toString() + "\n" +
                String.format(
                        "  Pais destino  : %s\n" +
                                "  Pasaporte     : %s\n" +
                                "  Cargo intl    : $%.0f",
                        destinationCountry,
                        requiresPassport ? "Si" : "No",
                        internationalFee
                );
    }
}
