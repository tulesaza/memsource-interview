package com.memsource.skeleton.logging;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    private static final String ACTION_ID_HTTP_HEADER = "Memsource-Action-Id";
    private static final String ACTION_ID_LOG_PROPERTY = "actionId";

    @Value("${request.long-runtime-ms:5000}")
    private int requestMaxRuntimeMs;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        final long start = System.nanoTime();
        try {
            beforeRequest(request, response);
        } catch (Throwable t) {
            LOGGER.error(t.getMessage(), t);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            afterRequest(response, start);
        }
    }

    private void beforeRequest(HttpServletRequest request, HttpServletResponse response) {
        final String memsourceActionId = request.getHeader(ACTION_ID_HTTP_HEADER);
        if (StringUtils.hasText(memsourceActionId)) {
            response.addHeader(ACTION_ID_HTTP_HEADER, memsourceActionId);
            MDC.put(ACTION_ID_LOG_PROPERTY, memsourceActionId);
        }
        logRequest(request);
    }

    private void afterRequest(HttpServletResponse response, long startNanos) {
        logResponse(response, System.nanoTime() - startNanos);
        MDC.clear();
    }

    private void logRequest(HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Remote-IP=[").append(request.getRemoteAddr()).append("] ");
            try {
                URI uri = new URI(request.getRequestURI());
                uri = new URI(uri.getScheme(), uri.getHost(), uri.getPath(), uri.getFragment());
                builder.append("URI=[").append(uri);
                if (uri.getQuery() != null) {
                    builder.append('?').append(new MaskedSequence(uri.getQuery().length()));
                }
                builder.append("] ");
            } catch (URISyntaxException e) {
                LOGGER.debug("Could not parse request URI: " + e.getMessage());
            }
            builder.append("Method=[").append(request.getMethod()).append("] ");
            logHeaders(builder, request);
            LOGGER.debug(builder.toString());
        }
    }

    private void logResponse(HttpServletResponse response, long runtimeNanos) {
        if (LOGGER.isDebugEnabled()) {
            long runtimeMillis = TimeUnit.NANOSECONDS.toMillis(runtimeNanos);
            StringBuilder builder = new StringBuilder();
            builder.append("Status=[").append(response.getStatus()).append("] ");
            logHeaders(builder, response);
            builder.append("Time=[").append(runtimeMillis).append("ms");
            if (runtimeMillis > requestMaxRuntimeMs) {
                builder.append(" REQUEST_LONG");
            }
            builder.append(']');
            LOGGER.debug(builder.toString());
        }
    }

    private void logHeaders(StringBuilder builder, HttpServletRequest request) {
        builder.append("Headers=[");
        Enumeration<String> headerNames = request.getHeaderNames();
        AtomicBoolean first = new AtomicBoolean(true);
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.toUpperCase(Locale.ENGLISH).contains("AUTH")) {
                String value = request.getHeader(headerName);
                logHeader(builder, headerName, value != null ? new MaskedSequence(value.length()) : null, first);
            } else {
                logHeader(builder, headerName, request.getHeader(headerName), first);
            }
        }
        builder.append("]");
    }

    private void logHeaders(StringBuilder builder, HttpServletResponse response) {
        builder.append("Headers=[");
        AtomicBoolean first = new AtomicBoolean(true);
        for (String headerName : response.getHeaderNames()) {
            logHeader(builder, headerName, response.getHeader(headerName), first);
        }
        builder.append("] ");
    }

    private void logHeader(StringBuilder builder, String headerName, CharSequence headerValue, AtomicBoolean first) {
        if (!ACTION_ID_HTTP_HEADER.equalsIgnoreCase(headerName)) {
            if (!first.compareAndSet(true, false)) {
                builder.append(", ");
            }
            builder.append(headerName).append("=[").append(headerValue).append(']');
        }
    }

}
