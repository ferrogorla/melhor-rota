package com.bexstech.model;

import com.bexstech.dto.RouteResponseDTO;

import java.util.*;

public class BestRoute extends AbstractCollection<Edge> {

    private List<Edge> pathList;
    private Vertex from;
    private Vertex to;
    private Integer price;

    public BestRoute(Vertex vertex) {
        pathList = new LinkedList<>();
        from = vertex;
        to = vertex;
        price = 0;
    }

    public BestRoute(BestRoute bestRoute) {
        this.pathList = new LinkedList<>(bestRoute.pathList);
        this.from = bestRoute.from;
        this.to = bestRoute.to;
        this.price = bestRoute.price;
    }

    public void addEdge(Edge edge) {
        pathList.add(edge);
    }

    @Override
    public Iterator<Edge> iterator() {
        return pathList.iterator();
    }

    @Override
    public int size() {
        return pathList.size();
    }

    @Override
    public String toString() {
        return getPathRoute(" - ") + " > $" + price;
    }

    public RouteResponseDTO toRouteResponseDTO() {
        return new RouteResponseDTO( getPathRoute(" - "), price );
    }

    public String getPathRoute(String delimiter) {
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        Vertex vertex;
        stringJoiner.add(from.getLabel());
        for (Edge e : pathList) {
            vertex = e.getTo();
            stringJoiner.add(vertex.getLabel());
        }

        return stringJoiner.toString();
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setDestination(Vertex destination) {
        this.to = destination;
    }

}
