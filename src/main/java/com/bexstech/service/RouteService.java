package com.bexstech.service;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.engine.DijkstraAlgorithm;
import com.bexstech.exception.ValidationException;
import com.bexstech.exception.RouteNotFoundException;
import com.bexstech.model.BestRoute;
import com.bexstech.model.Graph;
import com.bexstech.singleton.RouteSingleton;
import com.bexstech.util.ReadFile;
import com.bexstech.util.WriteFile;
import com.bexstech.validation.RouteValidate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RouteService {

    public BestRoute find(Graph graph, String route) throws ValidationException, RouteNotFoundException {
        RouteValidate.isValid(route);
        RouteRequestDTO currentRoute = RouteRequestDTO.from(route);
        DijkstraAlgorithm engine = new DijkstraAlgorithm(graph);
        return engine.calculateBestRoute(currentRoute.getFrom(), currentRoute.getTo());
    }

    public void insert(RouteRequestDTO routeRequestDTO) throws IOException, ValidationException {
        RouteValidate.isValid(routeRequestDTO);

        WriteFile.writeCSV(RouteSingleton.getInstance().getFilePath(), routeRequestDTO);

        Graph graph = ReadFile.getGraphFromCSV(RouteSingleton.getInstance().getFilePath());

        RouteSingleton.getInstance().updateGraph(graph);
    }

}
