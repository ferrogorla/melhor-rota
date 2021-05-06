package com.bexstech.validation;

import com.bexstech.dto.RouteRequestDTO;
import com.bexstech.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class RouteValidateTest {

    private static final String MESSAGE_ROUTE_IS_NULL_OR_EMPTY = "[WARNING] The route can not be null or empty";
    private static final String MESSAGE_ROUTE_HAS_INVALID_SEPARATOR = "[WARNING] Has to have separator to separate the route";
    private static final String MESSAGE_ROUTE_HAS_INVALID_SIZE = "[WARNING] Has to have 7 letters";
    private static final String MESSAGE_ROUTE_DTO_IS_NULL = "[WARNING] The from or to can not be null";
    private static final String MESSAGE_ROUTE_DTO_IS_EMPTY = "[WARNING] The from or to can not be empty";
    private static final String MESSAGE_ROUTE_DTO_IS_SAME_ROUTE = "[WARNING] The from can not be the same as to";
    private static final String MESSAGE_INITIAL_ROUTE_HAVE_WRONG_SIZE = "[WARNING] Each initials has to contain 3 letters";
    private static final String MESSAGE_ROUTE_CONTAINS_NUMBERS_OR_INVALID = "[WARNING] Route can not contain numbers or special characters";
    private static final String MESSAGE_ROUTE_INVALID_PRICE = "[WARNING] The price can not be null, zero or negative";

    @Test
    public void whenRouteIsNull() throws Exception {
        String route = null;

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(route);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_IS_NULL_OR_EMPTY.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteIsEmpty() throws Exception {
        String route = "";

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(route);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_IS_NULL_OR_EMPTY.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenSeparatorIsInvalid() throws Exception {
        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid("XXX_YYY");
        });

        Assertions.assertTrue(MESSAGE_ROUTE_HAS_INVALID_SEPARATOR.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteIsMoreThanSevenCharacteres() throws Exception {
        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid("XXXX-YYY");
        });

        Assertions.assertTrue(MESSAGE_ROUTE_HAS_INVALID_SIZE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteIsLessThanSevenCharacteres() throws Exception {
        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid("XX-YYY");
        });

        Assertions.assertTrue(MESSAGE_ROUTE_HAS_INVALID_SIZE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOIsNull() throws Exception {
        RouteRequestDTO routeRequestDTO = null;

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(routeRequestDTO);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_NULL.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOFromIsNull() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO(null, "XXX", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(routeRequestDTO);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_NULL.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOToIsNull() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", null, 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(routeRequestDTO);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_NULL.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOFromIsEmpty() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("", "XXX", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(routeRequestDTO);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_EMPTY.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOToIsEmpty() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid(routeRequestDTO);
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_EMPTY.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenHasTheSameRoute() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "XXX", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_ROUTE_DTO_IS_SAME_ROUTE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOFromIsDifferenteThanThree() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXXX", "XXX", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_INITIAL_ROUTE_HAVE_WRONG_SIZE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteRequestDTOToIsDifferenteThanThree() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "XXXX", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_INITIAL_ROUTE_HAVE_WRONG_SIZE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRouteContainsNumbersOrInvalidCharacters() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XX1", "XX2", 10);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_ROUTE_CONTAINS_NUMBERS_OR_INVALID.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRoutePriceIsNull() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "YYY", null);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_ROUTE_INVALID_PRICE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRoutePriceIsZero() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "YYY", 0);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_ROUTE_INVALID_PRICE.equals(badRequestException.getMessage()));
    }

    @Test
    public void whenRoutePriceIsNegative() throws Exception {
        RouteRequestDTO routeRequestDTO = new RouteRequestDTO("XXX", "YYY", -5);

        ValidationException badRequestException = assertThrows( ValidationException.class, () -> {
            RouteValidate.isValid( routeRequestDTO );
        });

        Assertions.assertTrue(MESSAGE_ROUTE_INVALID_PRICE.equals(badRequestException.getMessage()));
    }

}
