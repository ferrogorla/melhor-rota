package com.bexstech.engine;

import com.bexstech.exception.RouteNotFoundException;
import com.bexstech.model.BestRoute;
import com.bexstech.model.Edge;
import com.bexstech.model.Graph;
import com.bexstech.model.Vertex;

import java.util.HashSet;
import java.util.Set;

public class DijkstraAlgorithm {

    private Set<Vertex> unsettledNodes;
    private Vertex currentVertex;
    private Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public BestRoute calculateBestRoute(Vertex source, Vertex destination) {
        if (graph.getBestRoute() != null) {
            graph.resetGraph();
        }
        source.setAsSource();
        unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0 && !destination.equals(currentVertex)) {
            currentVertex = getVertex();
            for (Edge e : currentVertex) {
                updateNext(e);
            }
        }

        BestRoute br = destination.getBestRoute();
        if (br == null) {
            graph.resetGraph();
            throw new RouteNotFoundException("[WARNING] Route from '" + source + "' to '" + destination + "' doesn't exist");
        }
        this.graph.setBestRoute(br);
        return br;
    }

    public BestRoute calculateBestRoute(String source, String destination) {
        return calculateBestRoute(graph.getVertex(source), graph.getVertex(destination));
    }

    private Vertex getVertex() {
        Vertex vertex = null;
        int bestPrice = Integer.MAX_VALUE;
        for (Vertex v : unsettledNodes) {
            int nodePrice = v.getPrice();
            if (nodePrice < bestPrice) {
                bestPrice = nodePrice;
                vertex = v;
            }
        }
        vertex.visit();
        unsettledNodes.remove(currentVertex);
        return vertex;
    }

    private void updateNext(Edge e) {
        Vertex vertex = e.getTo();
        if (!vertex.getVisited()) {
            Integer newPrice = currentVertex.getPrice() + e.getPrice();
            if (vertex.getPrice() > newPrice) {
                vertex.setPrice(newPrice);
                BestRoute bestRoute = new BestRoute(currentVertex.getBestRoute());
                bestRoute.addEdge(e);
                vertex.setBestRoute(bestRoute);
                unsettledNodes.add(vertex);
            }
        }
    }
}
