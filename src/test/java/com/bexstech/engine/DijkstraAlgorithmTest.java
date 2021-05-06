package com.bexstech.engine;

import com.bexstech.exception.RouteNotFoundException;
import com.bexstech.model.BestRoute;
import com.bexstech.model.Graph;
import com.bexstech.util.ReadFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DijkstraAlgorithmTest {

    private DijkstraAlgorithm engine;

    @Before
    public void init() {
        Graph graph = ReadFile.getGraphFromCSV("src/test/resources/routes.csv");
        engine = new DijkstraAlgorithm(graph);
    }

    @Test
     public void shouldFindCorrectRouteAndPrice() throws Exception {
        String expectedString = "GRU - BRC - SCL - ORL - CDG";
        Integer expectedPrice = 40;
        BestRoute outcome = engine.calculateBestRoute("GRU", "CDG");
        assertAll("Route and Price",
                () -> assertEquals(expectedString, outcome.getPathRoute(" - ")),
                () -> assertEquals(expectedPrice, outcome.getPrice())
        );
    }

    @Test(expected = RouteNotFoundException.class)
    public void shouldNotFindReverseRouteAndPrice() throws Exception {
        engine.calculateBestRoute("CDG", "GRU");
    }

    @Test(expected = RouteNotFoundException.class)
    public void shouldThrowBadRequestException() throws Exception {
        engine.calculateBestRoute("XXX", "CRT");
    }

}
