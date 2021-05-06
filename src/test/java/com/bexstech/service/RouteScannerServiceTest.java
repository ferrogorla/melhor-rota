package com.bexstech.service;

import com.bexstech.model.Graph;
import com.bexstech.singleton.RouteSingleton;
import com.bexstech.util.ReadFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

@RunWith(MockitoJUnitRunner.class)
public class RouteScannerServiceTest {

    @InjectMocks
    private RouteScannerService routeScannerService;

    private final InputStream systemIn = System.in;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private ByteArrayInputStream testIn;

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        Graph graph = ReadFile.getGraphFromCSV("src/test/resources/routes.csv");
        RouteSingleton.getInstance().updateGraph(graph);
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    @Test
    public void shouldScanValidInputText() {
        final String testString = "GRU-CDG\nexit";
        provideInput(testString);

        StringBuilder sb = new StringBuilder();
        sb.append("Please enter the route:");
        sb.append(" ");
        sb.append("Best route: GRU - BRC - SCL - ORL - CDG > $40");
        sb.append("\r\n");
        sb.append("Please enter the route:");
        sb.append(" ");
        sb.append("exit");
        sb.append("\n");

        routeScannerService.scan();

        Assertions.assertEquals(sb.toString(), outContent.toString());
    }

    @Test
    public void shouldScanInvalidInputText() {
        final String testString = "GRU-CD1\nexit";
        provideInput(testString);

        StringBuilder sb = new StringBuilder();
        sb.append("Please enter the route:");
        sb.append(" ");
        sb.append("[WARNING] Route can not contain numbers or special characters");
        sb.append("\r\n");
        sb.append("Please enter the route:");
        sb.append(" ");
        sb.append("exit");
        sb.append("\n");

        routeScannerService.scan();

        Assertions.assertEquals(sb.toString(), outContent.toString());
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

}
