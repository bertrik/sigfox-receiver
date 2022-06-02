package nl.bertriksikken.sigfox.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sigfox")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ISigfoxRestApi {

    @POST
    @Path("/uplink")
    void uplink(@HeaderParam("User-Agent") String userAgent, String data);

    @POST
    @Path("/advanced")
    void advanced(@HeaderParam("User-Agent") String userAgent, String data);

    @POST
    @Path("/status")
    void status(@HeaderParam("User-Agent") String userAgent, String data);

}
