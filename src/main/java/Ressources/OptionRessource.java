package Ressources;


import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
public class OptionRessource {
    OptionBusiness optionBusiness = new OptionBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Option> getlist(){
        return optionBusiness.getListeOptions();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{code}")
    public Response getById(@PathParam("code") int code){

        Option opt= optionBusiness.getOptionByCode(code);
        if(opt != null){
            return Response.status(Response.Status.OK).entity(opt).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("opt not found").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getBySemestre(@QueryParam("s") int semestre){

        List<Option> opt= optionBusiness.getOptionsBySemestre(semestre);
        if(opt != null){
            return Response.status(Response.Status.OK).entity(opt).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("aucun semestre").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Option opt){
        if(optionBusiness.addOption(opt)){
            return Response.status(Response.Status.CREATED).entity("added with success").build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("echec not found").build();
        }
    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateOption(@PathParam("code") int code , Option opt){
        if(optionBusiness.getOptionByCode(code) != null){
            optionBusiness.updateOption(code, opt);
            return Response.status(Response.Status.CREATED).entity("update with success").build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("echec not found").build();
        }
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOption(@PathParam("code") int code){
        if(optionBusiness.getOptionByCode(code) != null){
            optionBusiness.deleteOption(code);
            return Response.status(Response.Status.CREATED).entity("delete with success").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("echec not found").build();
    }
}