package com.matthewcasperson.swarmdemo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import javax.ws.rs.core.MediaType;

/**
 * A camel route that uses the rest DSL
 */
class MyRoute extends RouteBuilder {

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
                .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3")
                /*
                    and enable CORS
                  */
                .apiProperty("cors", "true");

        /*
            Create a rest endpoint
         */
        rest("/hello")
                .description("An example of the Camel REST DSL")
                /*
                    We are returning plain text, so don't use the default binding
                    mode of JSON.
                 */
                .bindingMode(RestBindingMode.off)
                /*
                    response to get requests
                 */
                .get()
                /*
                    This is used by the swagger docs generator
                 */
                .description("A simple get endpoint with the REST DSL")
                /*
                    We produce plain text
                 */
                .produces(MediaType.TEXT_PLAIN)
                /*
                    embed a camel route in rest dsl
                 */
                .route()
                /*
                    this is standard camel to set the contents of the message body
                 */
                .setBody(simple("Hello ${header.name}!"));
    }
}