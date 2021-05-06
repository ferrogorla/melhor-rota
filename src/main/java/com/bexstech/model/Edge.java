package com.bexstech.model;

public class Edge {

    private Vertex from;
    private Vertex to;
    private Integer price;

    public Edge(Vertex from, Vertex to, int price) {
        if (price < 1) {
            throw new IllegalArgumentException("[WARNING] The price has to be an positive integer!");
        }
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public Vertex getTo() {
        return to;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Edge)) {
            return false;
        }

        Edge e = (Edge) other;
        return e.from.equals(this.from) && e.to.equals(this.to);
    }

    public int hashCode() {
        return (from.getLabel() + to.getLabel()).hashCode();
    }

}
