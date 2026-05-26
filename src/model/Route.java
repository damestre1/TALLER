package model;

public abstract class Route {

    private String routeCode;
    private String originCity;
    private String destinationCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double basePrice;
    private String status;

    public Route(
            String routeCode,
            String originCity,
            String destinationCity,
            String departureDate,
            String departureTime,
            String arrivalTime,
            int totalSeats,
            double basePrice,
            String status
    ){

        this.routeCode = routeCode;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.basePrice = basePrice;
        this.status = status;

    }

    public abstract double calculateFinalPrice();

    public String getRouteCode() {

        return routeCode;

    }

    public String getOriginCity() {

        return originCity;

    }

    public String getDestinationCity() {

        return destinationCity;

    }

    public String getDepartureDate() {

        return departureDate;

    }

    public String getDepartureTime() {

        return departureTime;

    }

    public String getArrivalTime() {

        return arrivalTime;

    }

    public int getTotalSeats() {

        return totalSeats;

    }

    public int getAvailableSeats() {

        return availableSeats;

    }

    public double getBasePrice() {

        return basePrice;

    }

    public String getStatus() {

        return status;

    }

    public void setAvailableSeats(
            int availableSeats
    ){

        this.availableSeats = availableSeats;

    }

    public void setBasePrice(
            double basePrice
    ){

        this.basePrice = basePrice;

    }

    public void setStatus(
            String status
    ){

        this.status = status;

    }

}