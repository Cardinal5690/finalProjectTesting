package com.testing.controller.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {
    @Mock
    HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
    @Mock
    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

    @Mock
    FilterChain filterChain = mock(FilterChain.class);


    @Test
    public void testAuthenticationFilter() throws IOException, ServletException {

        when(httpServletRequest.getRequestURI()).thenReturn("/other.jsp");

        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(httpServletRequest, httpServletResponse,
                filterChain);

        verify(httpServletResponse).sendRedirect("/testing/login");
    }
}