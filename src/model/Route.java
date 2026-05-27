package model;

public abstract class Route {
    private String code;
    private String originCity;
    private String destinationCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private int totalCapacity;
    private int availableSeats;
    private double basePrice;
    private String status;

    public Route(String code, String originCity, String destinationCity, String departureDate, String departureTime, String arrivalTime, int totalCapacity, double basePrice) {
        this.code = code;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalCapacity = totalCapacity;
        this.availableSeats = totalCapacity;
        this.basePrice = basePrice;
        this.status = "Programada";
    }

    public String getEstimatedDuration() {
        try {
            int depHour = Integer.parseInt(departureTime.substring(0, 2));
            int depMin  = Integer.parseInt(departureTime.substring(3));
            int arrHour = Integer.parseInt(arrivalTime.substring(0, 2));
            int arrMin  = Integer.parseInt(arrivalTime.substring(3));

            int totalDepMin = depHour * 60 + depMin;
            int totalArrMin = arrHour * 60 + arrMin;

            if (totalArrMin < totalDepMin) totalArrMin += 24 * 60;

            int diffMin = totalArrMin - totalDepMin;
            int hours   = diffMin / 60;
            int minutes = diffMin % 60;

            return hours + "h " + (minutes > 0 ? minutes + "min" : "");
        } catch (Exception e) {
            return "N/A";
        }
    }

    public abstract double getFinalPrice();

    public String getCode() { return code; }
    public String getOriginCity() { return originCity; }
    public String getDestinationCity() { return destinationCity; }
    public int getAvailableSeats() { return availableSeats; }
    public double getBasePrice() { return basePrice; }
    public String getStatus() { return status; }

    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format(
                "  Codigo        : %s\n" +
                        "  Origen        : %s\n" +
                        "  Destino       : %s\n" +
                        "  Fecha salida  : %s\n" +
                        "  Hora salida   : %s\n" +
                        "  Hora llegada  : %s\n" +
                        "  Duracion est. : %s\n" +
                        "  Puestos       : %d / %d disponibles\n" +
                        "  Precio final  : $%.0f\n" +
                        "  Estado        : %s",
                code, originCity, destinationCity,
                departureDate, departureTime, arrivalTime,
                getEstimatedDuration(),
                availableSeats, totalCapacity,
                getFinalPrice(), status
        );
    }
}
