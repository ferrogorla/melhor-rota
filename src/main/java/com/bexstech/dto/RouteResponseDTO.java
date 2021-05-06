package com.bexstech.dto;

public class RouteResponseDTO {

    private String route;
    private Integer price;

    public RouteResponseDTO(String route, Integer price) {
        this.route = route;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public String getRoute() {
        return route;
    }

}
