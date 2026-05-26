package service;

import exception.RouteNotFoundException;

import model.Route;
import repository.RouteRepository;

public class RouteService {

    private RouteRepository repository;

    public RouteService(
            RouteRepository repository
    ) {

        this.repository = repository;

    }

    public void createRoute(
            Route route
    ) throws Exception {

        if(
                repository.existsRoute(
                        route.getRouteCode()
                )
        ){

            throw new Exception(
                    "Ya existe una ruta con ese código"
            );

        }

        if(
                route.getTotalSeats() <= 0
        ){

            throw new Exception(
                    "La capacidad debe ser mayor a cero"
            );

        }

        if(
                route.getTotalSeats() > 32
        ){

            throw new Exception(
                    "La capacidad máxima es de 32 puestos"
            );

        }

        if(
                route.getBasePrice() <= 0
        ){

            throw new Exception(
                    "El precio de la ruta debe ser mayor a cero"
            );

        }

        repository.addRoute(
                route
        );

    }

    public Route findRoute(
            String routeCode
    ) throws Exception {

        Route route =
                repository.findRoute(
                        routeCode
                );

        if(
                route == null
        ){

            throw new RouteNotFoundException(
                    "No se encontró la ruta"
            );

        }

        return route;

    }

    public void updateStatus(
            String routeCode,
            String status
    ) throws Exception {

        Route route =
                findRoute(
                        routeCode
                );

        route.setStatus(
                status
        );

    }

}