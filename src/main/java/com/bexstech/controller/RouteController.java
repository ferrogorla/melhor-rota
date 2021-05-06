package com.bexstech.controller;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.service.RouteService;
import com.bexstech.singleton.RouteSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity findRoute(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok( routeService.find( RouteSingleton.getInstance().getGraph(), from + "-" + to ).toRouteResponseDTO() );
    }

    @PutMapping
    public ResponseEntity insertRoute(@RequestBody RouteRequestDTO routeRequestDTO) throws Exception {
        routeService.insert( routeRequestDTO );
        return ResponseEntity.noContent().build();
    }

}
