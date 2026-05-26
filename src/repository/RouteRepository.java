package repository;

import model.Route;

import java.util.ArrayList;

public class RouteRepository {

    private ArrayList<Route> routes;

    public RouteRepository() {

        routes = new ArrayList<>();

    }

    public void addRoute(
            Route route
    ) {

        routes.add(route);

    }

    public Route findRoute(
            String routeCode
    ) {

        for(
                Route route
                : routes
        ){

            if(
                    route
                            .getRouteCode()
                            .equals(routeCode)
            ){

                return route;

            }

        }

        return null;

    }

    public boolean existsRoute(
            String routeCode
    ){

        return findRoute(
                routeCode
        ) != null;

    }

    public ArrayList<Route>
    getRoutes(){

        return routes;

    }

}