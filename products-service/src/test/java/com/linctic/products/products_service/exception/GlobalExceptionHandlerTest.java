package com.linctic.products.products_service.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class GlobalExceptionHandlerTest {
    @Test
    void shouldReturnJsonApiErrorFormat_forIllegalArgumentException() {
        var handler = new GlobalExceptionHandler();
        var response = handler.handleIllegalArgument(new IllegalArgumentException("Bad input"));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertTrue(response.getBody().containsKey("errors"));
    }
}
