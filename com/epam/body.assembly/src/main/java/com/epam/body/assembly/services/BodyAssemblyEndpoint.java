package com.epam.body.assembly.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("assembly")
public interface BodyAssemblyEndpoint {

    @POST
    public void assemble();
}