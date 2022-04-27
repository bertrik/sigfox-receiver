package nl.bertriksikken.sigfox.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sigfox")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ISigfoxRestApi {

    //    http://stofradar.nl:9001/sigfox/{deviceTypeId}/up/{device}
    @POST
    @Path("/{deviceTypeId}/up/{device}")
    void uplink(@PathParam("deviceTypeId") String deviceTypeId, @PathParam("device") String device, String data);

}
