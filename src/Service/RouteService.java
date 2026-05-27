package service;
import model.NationalRoute;
import model.InternationalRoute;
import model.Route;
import repository.RouteRepository;

public class RouteService {
    private RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    private void validateCommonFields(String code, int totalCapacity, double basePrice) throws service.FluxioBusException {
        if (routeRepository.existsByCode(code)) {
            throw new service.FluxioBusException("Ya existe una ruta con ese codigo");
        }
        if (totalCapacity <= 0 || totalCapacity > 32) {
            throw new service.FluxioBusException("La capacidad total debe ser mayor a 0 y maximo 32 puestos");
        }
        if (basePrice <= 0) {
            throw new service.FluxioBusException("El precio de la ruta debe ser mayor a cero");
        }
    }

    public void registerNationalRoute(String code, String originCity, String destinationCity, String departureDate, String departureTime, String arrivalTime, int totalCapacity, double basePrice, boolean hasRestStops) throws service.FluxioBusException {
        validateCommonFields(code, totalCapacity, basePrice);
        NationalRoute route = new NationalRoute(code, originCity, destinationCity, departureDate, departureTime, arrivalTime, totalCapacity, basePrice, hasRestStops);
        routeRepository.add(route);
    }

    public void registerInternationalRoute(String code, String originCity, String destinationCity, String departureDate, String departureTime, String arrivalTime, int totalCapacity, double basePrice, String destinationCountry, boolean requiresPassport, double internationalFee) throws service.FluxioBusException {
        validateCommonFields(code, totalCapacity, basePrice);
        InternationalRoute route = new InternationalRoute(code, originCity, destinationCity, departureDate, departureTime, arrivalTime, totalCapacity, basePrice, destinationCountry, requiresPassport, internationalFee);
        routeRepository.add(route);
    }

    public Route getRoute(String code) throws service.FluxioBusException {
        Route r = routeRepository.findByCode(code);
        if (r == null) throw new service.FluxioBusException("No se encontro la ruta con ese codigo");
        return r;
    }
}