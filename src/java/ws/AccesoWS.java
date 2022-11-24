/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("acceso")
public class AccesoWS {

    @Context
    private UriInfo context;

    public AccesoWS() {
    }
    
    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarByID(){
        
        return "Hola";
    }

  
}
