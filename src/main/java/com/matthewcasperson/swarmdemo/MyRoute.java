package com.matthewcasperson.swarmdemo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

import javax.ws.rs.core.MediaType;

/**
 * A camel route that uses the rest DSL
 */
class MyRoute extends RouteBuilder {

    @Override
    public void configure() {
        /*
            Create a rest endpoint
         */
        rest("/hello")
                /*
                    This is used by the swagger docs generator
                 */
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
                    Document our query param
                 */
                .param()
                    .name("name")
                    .type(RestParamType.query)
                    .description("The name to be displayed in the ourput of this request")
                    .endParam()
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