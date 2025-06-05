/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package WSA7;

import DAO.Bath;
import DAO.ServiceDao;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Ramon Godoy
 */
@Path("bath")
public class A7 {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of A7
     */
    public A7() {
    }

    /**
     * Retrieves representation of an instance of WSA7.A7
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getServicosBanho() {
        ServiceDao serviceDao = new ServiceDao();
        List<Bath> listServices = serviceDao.getAllServices();

        Gson gson = new Gson();

        return gson.toJson(listServices);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getServiceId(@PathParam("id") int id) {
        ServiceDao serviceDao = new ServiceDao();
        Bath bath = serviceDao.getIdService(id);

        if (bath != null) {
            Gson gson = new Gson();
            return gson.toJson(bath);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBath(String content) {
        // Implementar adição de novo serviço
        Gson gson = new Gson();
        Bath bath = gson.fromJson(content, Bath.class);

        ServiceDao serviceDao = new ServiceDao();
        try {
            serviceDao.addService(bath);
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBath(String content) {
        // Implementar adição de novo serviço
        Gson gson = new Gson();
        Bath bath = gson.fromJson(content, Bath.class);
        ServiceDao serviceDao = new ServiceDao();

        try {
            if (serviceDao.updateService(bath)) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteService(@PathParam("id") int id){
        ServiceDao serviceDao = new ServiceDao();
        
        if(serviceDao.remove(id)){
            return Response.status(Response.Status.OK).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
