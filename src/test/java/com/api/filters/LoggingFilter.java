package com.api.filters;

import com.api.listeners.TestListeners;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);
    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification,
            FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        loggingRequest(filterableRequestSpecification);
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        loggingResponse(response);
        return response;
    }
    
    private void loggingRequest(FilterableRequestSpecification req) {
        logger.info("Request Header : \n {}", req.getHeaders());
        if (req.getBody() != null) logger.info("Request Body : \n {}", req.getBody().toString());
    }
    
    private void loggingResponse(Response response) {
        logger.info("Response headers \n {}", response.getHeaders());
        logger.info("Response body \n {}", response.asPrettyString());
    }
}
