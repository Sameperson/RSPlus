package org.sameperson.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

@Path("test")
public class TestResource {

    @GET
    @Produces("text/shortDate")
    public Date testMethod(){
        return Calendar.getInstance().getTime();
    }

}
