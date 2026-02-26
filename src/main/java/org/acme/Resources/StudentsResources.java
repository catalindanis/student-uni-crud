package org.acme.Resources;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.Domain.Student;
import org.acme.Domain.StudentDTO;
import org.acme.DomainInteraction.StudentMapper;

import java.util.List;

@Path("/students")
public class StudentsResources {

    @Inject
    StudentMapper studentMapper;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        student.persist();
        return student;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("id") Long id) {
        Student student = Student.findById(id);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        return student;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("id") Long id, StudentDTO studentDTO) {
        Student student = Student.findById(id);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        return student;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("id") Long id) {
        Student student = Student.findById(id);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        student.delete();
        return student;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return Student.listAll();
    }
}