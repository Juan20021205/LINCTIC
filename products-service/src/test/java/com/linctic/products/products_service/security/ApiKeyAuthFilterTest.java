package com.linctic.products.products_service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import jakarta.servlet.FilterChain;

public class ApiKeyAuthFilterTest {
    @Test
    void shouldRejectRequestWithoutApiKey() throws Exception {
        var filter = new ApiKeyAuthFilter();
        ReflectionTestUtils.setField(filter, "apiKey", "12345");

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilterInternal(request, response, chain);
        assertEquals(401, response.getStatus());
    }
}
