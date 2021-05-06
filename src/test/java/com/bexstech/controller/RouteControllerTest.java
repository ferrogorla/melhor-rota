package com.bexstech.controller;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.dto.RouteResponseDTO;
import com.bexstech.model.BestRoute;
import com.bexstech.model.Vertex;
import com.bexstech.service.RouteScannerService;
import com.bexstech.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, args = "--file=src/test/resources/routes.csv")
public class RouteControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RouteService routeService;

    @MockBean
    private RouteScannerService routeScannerService;

    @Test
    public void shouldRequestRoute() throws Exception {
        URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/route")
                .queryParam("from", "GRU")
                .queryParam("to", "CDG")
                .build()
                .toUri();
        Vertex edges = new Vertex("GRU-CDG");
        BestRoute bestRoute = new BestRoute(edges);
        bestRoute.setPrice(20);

        doReturn(bestRoute).when(routeService).find(any(), any());

        RouteResponseDTO routeResponseDTO = restTemplate.getForObject(targetUrl, RouteResponseDTO.class);

        assertAll("Route and Price",
                () -> assertEquals(routeResponseDTO.getRoute(), "GRU-CDG"),
                () -> assertEquals(routeResponseDTO.getPrice(), 20)
        );
        verify(routeService).find(any(), any());
    }

    @Test
    public void shouldInsertRoute() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("GRU", "FLN", 25);
        HttpEntity<RouteRequestDTO> request = new HttpEntity<>(routeRequestDTO);

        ResponseEntity<Void> response = restTemplate
                .exchange("http://localhost:" + port + "/route", HttpMethod.PUT,
                        request, Void.class);

        verify(routeService).insert(any());
    }

}
