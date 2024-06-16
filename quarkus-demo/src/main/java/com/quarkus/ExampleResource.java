package com.quarkus;

import com.quarkus.service.DemoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @Inject
    DemoService demoService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
    @GET
    @Path("2")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello2() {
        return demoService.doSomething ();
    }
}
