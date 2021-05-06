package com.bexstech.service;

import com.bexstech.exception.RouteNotFoundException;
import com.bexstech.exception.ValidationException;
import com.bexstech.singleton.RouteSingleton;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class RouteScannerService {

    private static String EXIT = "exit";

    public void scan() {

        Scanner scanner = new Scanner(System.in);
        RouteService routeService;
        String route;

        while (true) {

            System.out.print("Please enter the route: ");

            route = scanner.nextLine();

            if (EXIT.equalsIgnoreCase(route)) {
                System.out.println(route);
                break;
            }

            try {
                routeService = new RouteService();
                System.out.println("Best route: " + routeService.find(RouteSingleton.getInstance().getGraph(), route).toString());
            } catch (IllegalArgumentException | RouteNotFoundException | ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

}
