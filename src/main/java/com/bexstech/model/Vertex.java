package com.bexstech.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertex implements Iterable<Edge> {

    private ArrayList<Edge> next;
    private String label;
    private Integer price;
    private BestRoute bestRoute;
    private Boolean visited;

    public Vertex(String label) {
        this.label = label;
        this.price = Integer.MAX_VALUE;
        this.next = new ArrayList<>();
        this.visited = false;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public Iterator<Edge> iterator() {
        return next.iterator();
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean hasNext(Edge edge) {
        return this.next.contains(edge);
    }

    public void addNext(Edge edge) {
        if (!next.contains(edge)) {
            this.next.add(edge);
        }
    }

    public void setAsSource() {
        visited = true;
        price = 0;
        bestRoute = new BestRoute(this);
    }

    public BestRoute getBestRoute() {
        return bestRoute;
    }

    public void setBestRoute(BestRoute bestRoute) {
        bestRoute.setPrice(this.price);
        bestRoute.setDestination(this);
        this.bestRoute = bestRoute;
    }

    public void reset() {
        this.price = Integer.MAX_VALUE;
        this.visited = false;
        this.bestRoute = null;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

}
