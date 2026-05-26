package model;

public class InternationalRoute extends Route {

    private String destinationCountry;
    private boolean passportRequired;
    private double internationalFee;

    public InternationalRoute(
            String routeCode,
            String originCity,
            String destinationCity,
            String departureDate,
            String departureTime,
            String arrivalTime,
            int totalSeats,
            double basePrice,
            String status,
            String destinationCountry,
            boolean passportRequired,
            double internationalFee
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

        this.destinationCountry = destinationCountry;
        this.passportRequired = passportRequired;
        this.internationalFee = internationalFee;

    }

    @Override
    public double calculateFinalPrice() {

        return getBasePrice() + internationalFee;

    }

    public String getDestinationCountry() {

        return destinationCountry;

    }

    public void setDestinationCountry(
            String destinationCountry
    ) {

        this.destinationCountry = destinationCountry;

    }

    public boolean isPassportRequired() {

        return passportRequired;

    }

    public void setPassportRequired(
            boolean passportRequired
    ) {

        this.passportRequired = passportRequired;

    }

    public double getInternationalFee() {

        return internationalFee;

    }

    public void setInternationalFee(
            double internationalFee
    ) {

        this.internationalFee = internationalFee;

    }

    @Override
    public String toString() {

        return
                super.toString() +
                        "\nPais destino: " +
                        destinationCountry +
                        "\nRequiere pasaporte: " +
                        passportRequired +
                        "\nCargo internacional: " +
                        internationalFee +
                        "\nPrecio final: " +
                        calculateFinalPrice();

    }

}