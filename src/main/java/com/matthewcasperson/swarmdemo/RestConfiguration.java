package com.matthewcasperson.swarmdemo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Configure the REST DSL
 */
public class RestConfiguration extends RouteBuilder {

    @Override
    public void configure() {
        /*
            Configure we want to use servlet as the component for the rest DSL
            and we enable json binding mode
         */
        restConfiguration()
                /*
                    integrate the rest dsl to the servlet component
                 */
                .component("servlet")
                /*
                    default to json
                 */
                .bindingMode(RestBindingMode.json)
                /*
                    and output using pretty print
                 */
                .dataFormatProperty("prettyPrint", "true")
                /*
                    add swagger api-doc out of the box
                  */
                .apiContextPath("/api-docs")
                /*
                    Define some metadata about our api
                 */
                .apiProperty("api.title", "WildFly Swarm Camrl REST API")
                .apiProperty("api.version", "1.2.3")
                .apiProperty("base.path", "/camel")
                /*
                    and enable CORS
                  */
                .apiProperty("cors", "true");
    }
}