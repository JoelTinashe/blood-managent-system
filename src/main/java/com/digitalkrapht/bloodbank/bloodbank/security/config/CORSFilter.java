package com.digitalkrapht.bloodbank.bloodbank.security.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CORSFilter implements Filter {

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");
    /**
     * CORS filter for http-companies and vehicles
     */
    public CORSFilter() {
    }

    /**
     * Do Filter on every http-companies.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            // Access-Control-Allow-Origin
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
            response.setHeader("Vary", "Origin");

            // Access-Control-Max-Age
            response.setHeader("Access-Control-Max-Age", "3600");

            // Access-Control-Allow-Credentials
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // Access-Control-Allow-Methods
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // Access-Control-Allow-Headers
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
            System.out.println(request.getMethod());
            if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
                try {
                    chain.doFilter(req, res);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Pre-flight");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
                        "USERID"+"ROLE"+
                        "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe,X-CSRF-TOKEN");
                response.setStatus(HttpServletResponse.SC_OK);
                chain.doFilter(req, res);
            }
        }


    }

    /**
     * Destroy method
     */
    @Override
    public void destroy() {
    }

    /**
     * Initialize CORS filter
     */
    @Override
    public void init(FilterConfig arg0) {
    }
}
