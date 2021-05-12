package com.epam.config.api;

import com.epam.config.api.model.Config;

import javax.ws.rs.*;



@Path("config")
public interface ConfigService {
    @GET
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    Config get(@QueryParam("key") String key);

    @DELETE
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    Config remove(@QueryParam("key") String key);

    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    Config add(Config config);
}