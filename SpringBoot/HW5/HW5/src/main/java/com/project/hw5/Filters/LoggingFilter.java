package com.project.hw5.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {


    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Instant requestTime = Instant.now(); // ✅ Capture exact request time

        // Format the timestamp in readable format
        String formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withLocale(Locale.ENGLISH)
                .withZone(ZoneId.systemDefault())  // Change to UTC if needed
                .format(requestTime);

        logger.info("Request Time: {} | Method: [{}] | URL: {} | IP: {}",
                formattedTime, request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

        filterChain.doFilter(request, response);

        Instant endTime = Instant.now();
        long duration = Duration.between(requestTime, endTime).toMillis();

        logger.info("Outgoing Response: [{}] {} - Status: {} ({} ms)",
                request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
    }
}
