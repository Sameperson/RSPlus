package org.sameperson.rest.resources;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/test")
public class MyResource {

    private int count;
    @PathParam("pathParam")
    private String pathParam;
    @QueryParam("query")
    private String queryParam;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        count++;
        return "Path param used: " + pathParam + " and query param: " + queryParam;
    }

}
