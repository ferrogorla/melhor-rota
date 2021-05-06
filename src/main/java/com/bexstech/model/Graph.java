package com.bexstech.model;

import com.bexstech.exception.RouteNotFoundException;

import java.util.HashMap;

public class Graph {

    private HashMap<String, Vertex> vertices;
    private HashMap<Integer, Edge> edges;
    private BestRoute bestRoute;

    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public boolean addConnected(String vertex1, String vertex2, int price) {
        Vertex from = vertices.computeIfAbsent(vertex1, Vertex::new);
        Vertex to = vertices.computeIfAbsent(vertex2, Vertex::new);
        return addEdge(from, to, price);
    }

    public boolean addEdge(Vertex from, Vertex to, int price) {
        if (from.equals(to)) {
            return false;
        }

        Edge e = new Edge(from, to, price);
        if (edges.containsKey(e.hashCode())) {
            return false;
        } else if (!(vertices.containsValue(from) || vertices.containsValue(to))) {
            return false;
        } else if (from.hasNext(e) || to.hasNext(e)) {
            return false;
        }

        edges.put(e.hashCode(), e);
        from.addNext(e);
        to.addNext(e);

        return true;
    }

    public Vertex getVertex(String label) throws RouteNotFoundException {
        if (vertices.containsKey(label)) {
            return vertices.get(label);
        }
        throw new RouteNotFoundException("[WARNING] '" + label + "' vertex not found in graph");
    }

    public void resetGraph() {
        vertices.values().forEach(Vertex::reset);
        bestRoute = null;
    }

    public BestRoute getBestRoute() {
        return bestRoute;
    }

    public void setBestRoute(BestRoute bestRoute) {
        this.bestRoute = bestRoute;
    }

}
