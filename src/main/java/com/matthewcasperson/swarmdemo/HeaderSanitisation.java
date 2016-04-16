package com.matthewcasperson.swarmdemo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Clean up the headers that are returned to a HTTP client by a Camel route
 */
@WebFilter("/*")
public class HeaderSanitisation implements Filter {

    /**
     * Headers we want to send back to the client
     * https://en.wikipedia.org/wiki/List_of_HTTP_header_fields#Response_fields has a more complete list
     */
    private static final List<String> WHITELIST_HEADERS = Arrays.asList(
            "Cache-Control",
            "Connection",
            "Date",
            "Pragma",
            "Trailer",
            "Transfer-Encoding",
            "Upgrade",
            "Via",
            "Warning",
            "Accept-Ranges",
            "Age",
            "ETag",
            "Location",
            "Proxy-Authenticate",
            "Retry-After",
            "Server",
            "Vary",
            "WWW-Authenticate"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
            public void setHeader(final String name, final String value) {
                if (WHITELIST_HEADERS.stream().anyMatch(e -> e.equalsIgnoreCase(name))) {
                    super.setHeader(name, value);
                }
            }

            public void addHeader(final String name, final String value) {
                if (WHITELIST_HEADERS.stream().anyMatch(e -> e.equalsIgnoreCase(name))) {
                    super.addHeader(name, value);
                }
            }
        });
    }

    @Override
    public void destroy() {

    }
}
