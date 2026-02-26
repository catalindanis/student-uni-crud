package org.acme.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.Domain.University;
import org.acme.Domain.UniversityDTO;
import org.acme.DomainInteraction.UniversityMapper;

import java.util.List;

@Path("/universities")
public class UniversityResources {

    @Inject
    UniversityMapper universityMapper;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public University createUniversity(UniversityDTO universityDTO) {
        University university = universityMapper.toEntity(universityDTO);
        university.persist();
        return university;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public University getUniversityById(@PathParam("id") Long id) {
        University university = University.findById(id);

        if (university == null) {
            throw new NotFoundException("University not found");
        }

        return university;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public University updateUniversity(@PathParam("id") Long id, UniversityDTO universityDTO) {
        University university = University.findById(id);

        if (university == null) {
            throw new NotFoundException("University not found");
        }

        university.setName(universityDTO.getName());
        university.setLocation(universityDTO.getLocation());

        return university;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public University deleteUniversity(@PathParam("id") Long id) {
        University university = University.findById(id);

        if (university == null) {
            throw new NotFoundException("University not found");
        }

        university.delete();
        return university;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<University> getAllUniversities() {
        return University.listAll();
    }
}

