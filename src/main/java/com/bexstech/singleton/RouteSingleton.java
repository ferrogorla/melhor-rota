package com.bexstech.singleton;

import java.util.ArrayList;
import java.util.List;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.model.Graph;

public class RouteSingleton {

    private static RouteSingleton instance;
    private List<RouteRequestDTO> routeRequestDTOS;
    private String filePath;
    private Graph graph;

    private RouteSingleton() {
        routeRequestDTOS = new ArrayList<>();
        filePath = "";
    }

    public synchronized static RouteSingleton getInstance(){
        if(instance == null) {
            instance = new RouteSingleton();
        }
        return instance;
    }

    public synchronized void updateRoutes(List<RouteRequestDTO> routeRequestDTOS) {
        this.routeRequestDTOS = routeRequestDTOS;
    }

    public synchronized void updateFilePath(String filePath) {
        this.filePath = filePath;
    }

    public synchronized void updateGraph(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public List<RouteRequestDTO> getRouteModels() {
        return routeRequestDTOS;
    }

    public String getFilePath() {
        return filePath;
    }

}
