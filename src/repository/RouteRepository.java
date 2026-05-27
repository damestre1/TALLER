package repository;
import model.Route;
import java.util.ArrayList;

public class RouteRepository {
    private ArrayList<Route> routes = new ArrayList<>();

    public void add(Route route) {
        routes.add(route);
    }

    public Route findByCode(String code) {
        for (Route r : routes) {
            if (r.getCode().equals(code)) return r;
        }
        return null;
    }

    public boolean existsByCode(String code) {
        return findByCode(code) != null;
    }
}
