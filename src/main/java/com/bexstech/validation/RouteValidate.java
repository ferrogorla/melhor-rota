package com.bexstech.validation;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.exception.ValidationException;

public class RouteValidate {

    public static void isValid(String route) throws ValidationException {
        isNotNullOrEmpty( route );
        hasSeparator( route );
        hasSevenLetters( route );
        isValid( new RouteRequestDTO( route.split( "-" )[0], route.split( "-" )[1], 1 ) );
    }

    public static void isValid(RouteRequestDTO routeRequestDTO) throws ValidationException {
        isNotNull( routeRequestDTO );
        isNotEmpty( routeRequestDTO );
        isNotSameRoute( routeRequestDTO );
        sizeIsThree( routeRequestDTO );
        isOnlyLetters( routeRequestDTO );
        isValidPrice( routeRequestDTO );
    }

    private static void hasSevenLetters(String route) throws ValidationException {
        if(!(route.length() == 7)) {
            throw new ValidationException( "[WARNING] Has to have 7 letters" );
        }
    }

    private static void hasSeparator(String route) throws ValidationException {
        if(!route.contains( "-" )) {
            throw new ValidationException( "[WARNING] Has to have separator to separate the route" );
        }
    }

    private static void isNotNullOrEmpty(String route) throws ValidationException {
        if(route == null ||	route.isEmpty()) {
            throw new ValidationException( "[WARNING] The route can not be null or empty" );
        }
    }

    private static void isValidPrice(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(routeRequestDTO.getPrice() == null || routeRequestDTO.getPrice() <= 0) {
            throw new ValidationException( "[WARNING] The price can not be null, zero or negative" );
        }
    }

    private static void isOnlyLetters(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(!routeRequestDTO.getFrom().matches( "^[a-zA-Z]*$" ) ||
                !routeRequestDTO.getTo().matches( "^[a-zA-Z]*$" )) {
            throw new ValidationException( "[WARNING] Route can not contain numbers or special characters" );
        }
    }

    private static void sizeIsThree(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(!(routeRequestDTO.getFrom().length() == 3) ||
                !(routeRequestDTO.getTo().length() == 3)) {
            throw new ValidationException( "[WARNING] Each initials has to contain 3 letters" );
        }
    }

    private static void isNotSameRoute(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(routeRequestDTO.getFrom().equalsIgnoreCase( routeRequestDTO.getTo() )) {
            throw new ValidationException( "[WARNING] The from can not be the same as to" );
        }
    }

    private static void isNotEmpty(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(routeRequestDTO.getFrom().isEmpty() ||
                routeRequestDTO.getTo().isEmpty()) {
            throw new ValidationException( "[WARNING] The from or to can not be empty" );
        }
    }

    private static void isNotNull(RouteRequestDTO routeRequestDTO) throws ValidationException {
        if(routeRequestDTO == null || routeRequestDTO.getFrom() == null ||
                routeRequestDTO.getTo() == null ) {
            throw new ValidationException( "[WARNING] The from or to can not be null" );
        }
    }

}
