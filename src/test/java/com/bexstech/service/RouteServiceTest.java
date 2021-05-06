package com.bexstech.service;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.exception.RouteNotFoundException;
import com.bexstech.exception.ValidationException;
import com.bexstech.model.Graph;
import com.bexstech.singleton.RouteSingleton;
import com.bexstech.util.ReadFile;
import com.bexstech.util.WriteFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;

    @Before
    public void init() {
        Graph graph = ReadFile.getGraphFromCSV("src/test/resources/routes.csv");
        RouteSingleton.getInstance().updateGraph(graph);
    }

    @Test
    public void shouldFindRoute() throws Exception {
        String route = "GRU-CDG";

        String bestRoute = routeService.find(RouteSingleton.getInstance().getGraph(), route).toString();

        assertThat(bestRoute).isEqualTo("GRU - BRC - SCL - ORL - CDG > $40");
    }

    @Test
    public void shouldFindRouteAndIgnoreCyclicRoute() throws Exception {
        String route = "GRU-ORL";

        String bestRoute = routeService.find(RouteSingleton.getInstance().getGraph(), route).toString();

        assertThat(bestRoute).isEqualTo("GRU - BRC - SCL - ORL > $35");
    }

    @Test(expected = ValidationException.class)
    public void shouldIgnoreCyclicRoute() throws Exception {
        String route = "GRU-GRU";

        routeService.find(RouteSingleton.getInstance().getGraph(), route);
    }

    @Test(expected = ValidationException.class)
    public void shouldIgnoreEmptyRoute() throws Exception {
        String route = "";

        routeService.find(RouteSingleton.getInstance().getGraph(), route);
    }

    @Test(expected = ValidationException.class)
    public void shouldIgnoreNumericRoute() throws Exception {
        String route = "G1R-CDG";

        routeService.find(RouteSingleton.getInstance().getGraph(), route);
    }

    @Test(expected = RouteNotFoundException.class)
    public void shouldIgnoreUnknownRoute() throws Exception {
        String route = "GRU-XXX";

        routeService.find(RouteSingleton.getInstance().getGraph(), route);
    }

    @Test
    public void shouldInsertNewRoute() throws Exception {
        try (MockedStatic<WriteFile> mockedUuid = Mockito.mockStatic(WriteFile.class)) {
            RouteRequestDTO route = new RouteRequestDTO("FLN", "BEL", 35);

            routeService.insert(route);
        }
    }

    @Test(expected = ValidationException.class)
    public void shouldNotInsertRouteWithNoPrice() throws Exception {
        RouteRequestDTO route = new RouteRequestDTO("FLN", "BEL", null);

        routeService.insert(route);
    }

    @Test(expected = ValidationException.class)
    public void shouldNotInsertCyclicRoute() throws Exception {
        RouteRequestDTO route = new RouteRequestDTO("FLN", "FLN", 0);

        routeService.insert(route);
    }

    @Test(expected = ValidationException.class)
    public void shouldNotInsertEmptyRoute() throws Exception {
        RouteRequestDTO route = new RouteRequestDTO("FLN", "", 0);

        routeService.insert(route);
    }

}
