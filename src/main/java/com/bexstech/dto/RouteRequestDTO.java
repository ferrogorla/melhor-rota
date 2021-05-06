package com.bexstech.dto;

public class RouteRequestDTO {

    private String from;
    private String to;
    private Integer price;

    public RouteRequestDTO(String from, String to, Integer price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }

    public String getFrom() {
        if(from != null) {
            return from.toUpperCase();
        }
        return null;
    }

    public String getTo() {
        if(to != null) {
            return to.toUpperCase();
        }
        return null;
    }

    public Integer getPrice() {
        return price;
    }

    public static RouteRequestDTO from(String routeInput) {
        String[] route = routeInput.split("-");
        return new RouteRequestDTO( route[0], route[1], 0 );
    }

    @Override public String toString() {
        return this.from + " - " + this.to + " > $" + this.price;
    }

}
