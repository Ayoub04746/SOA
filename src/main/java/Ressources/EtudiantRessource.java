package Ressources;


import entities.Etudiant;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class EtudiantRessource {
    EtudiantBusiness eb = new EtudiantBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> getEtudiant() {return eb.getAllEtudiants();}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{identifiant}")
    public Response getById(@PathParam("identifiant") String identifiant){
        Etudiant e=eb.getEtudiantByIdentifiant(identifiant);
        if(e!=null){
            return Response.status(Response.Status.OK).entity(e).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Not Found").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Etudiant e){
        if(eb.addEtudiant(e)){
            return Response.status(Response.Status.CREATED).entity("Ajouter avec succe").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erreur").build();
    }

    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(@PathParam("identifiant") String identifiant,Etudiant e){
        if(eb.getEtudiantByIdentifiant(identifiant)!=null){
            eb.updateEtudiant(String.valueOf(identifiant), e);
            return Response.status(Response.Status.OK).entity("Modifier avec succe").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Erreur").build();

    }

    @DELETE
    @Path("/{identifiant}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("identifiant") String identifiant){
        if (eb.getEtudiantByIdentifiant(identifiant)!=null){
            eb.deleteEtudiant(identifiant);
            return Response.status(Response.Status.OK).entity("Supprimer avec succe").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Erreur").build();
    }
}
