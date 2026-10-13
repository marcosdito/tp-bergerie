package fr.esisar.bergerie.mouton;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;


@Path("/moutons")
public class MoutonResource {
	// GET /moutons
    @Inject
    MoutonMapper mapper;
    @GET
    public List<MoutonResponseDTO> getAll() {
        List<Mouton> moutons = Mouton.listAll();
        return moutons.stream().map(mapper::toResponse).toList();
    }
    
    // GET /moutons/{mouton-id}
    @GET
    @Path("/{mouton-id}")
    public Response getById(@PathParam("/{moutonId}") long moutonId) {
    	Mouton mouton = Mouton.findById("{/id}");
    	if (mouton == null) {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
    	return Response.ok(mapper.toResponse(mouton)).build();
    }
    
    // GET /moutons/nes-apres
    @GET
    @Path("/nes-apres")
    public Response getNesApres(@QueryParam("date") LocalDate date) {
        if (date == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<Mouton> moutons = Mouton.list("dateNaissance > ?1", date);
        return Response.ok(moutons.stream().map(mapper::toResponse).toList()).build();
    }
    
    // POST /moutons
    @POST
    @Transactional
    public Response create(@Valid CreateMoutonDTO dto) {
        if (Mouton.count("nom", dto.nom()) > 0) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        Mouton mouton = mapper.toEntity(dto);
        mouton.persist();
        return Response.status(Response.Status.CREATED).entity(mapper.toResponse(mouton)).build();
    }
    
    // PATCH /moutons/{moutonId}
    @PATCH
    @Path("/{moutonId}")
    @Transactional
    public Response update(@PathParam("moutonId") Long moutonId, @Valid PatchMoutonDTO dto) {
        Mouton mouton = Mouton.findById(moutonId);
        if (mouton == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (dto.nom() != null && Mouton.count("nom = ?1 and id != ?2", dto.nom(), moutonId) > 0) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        mapper.patch(dto, mouton);
        return Response.ok(mapper.toResponse(mouton)).build();
    }

    // DELETE /moutons/{moutonId}
    @DELETE
    @Path("/{moutonId}")
    @Transactional
    public Response delete(@PathParam("moutonId") Long moutonId) {
        Mouton mouton = Mouton.findById(moutonId);
        if (mouton == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        mouton.delete();
        return Response.noContent().build();
    }
}
