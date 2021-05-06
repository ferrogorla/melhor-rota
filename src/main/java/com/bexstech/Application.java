package com.bexstech;

import com.bexstech.model.Graph;
import com.bexstech.service.RouteScannerService;
import com.bexstech.singleton.RouteSingleton;
import com.bexstech.util.ReadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Value("${file}")
    private String filePath;

    private RouteScannerService routeScannerService;

    public Application(RouteScannerService routeScannerService) {
        this.routeScannerService = routeScannerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Graph graph = ReadFile.getGraphFromCSV(filePath);
        RouteSingleton.getInstance().updateGraph(graph);
        RouteSingleton.getInstance().updateFilePath(filePath);
        routeScannerService.scan();
    }

}
