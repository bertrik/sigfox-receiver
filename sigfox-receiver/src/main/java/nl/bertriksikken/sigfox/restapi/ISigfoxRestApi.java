package nl.bertriksikken.sigfox.restapi;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sigfox")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ISigfoxRestApi {

    @POST
    @Path("/uplink")
    void uplink(@HeaderParam("User-Agent") String userAgent, String data);

    @POST
    @Path("/advanced")
    void advanced(@HeaderParam("User-Agent") String userAgent, DataAdvanced data);

    @POST
    @Path("/status")
    void status(@HeaderParam("User-Agent") String userAgent, String data);

}
