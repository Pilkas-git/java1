package lt.vu.Controllers;

import lombok.Getter;
import lombok.Setter;
import lt.vu.Controllers.contracts.StudentDTO;
import lt.vu.entities.Student;
import lt.vu.persistence.StudentsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentsController {

    @Inject
    @Getter @Setter
    private StudentsDAO studentsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.findOne(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setNumber(student.getNumber());

        return Response.ok(studentDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(StudentDTO studentData) {
        try {
            Student studentToCreate = new Student();
            studentToCreate.setFirstName(studentData.getFirstName());
            studentToCreate.setLastName(studentData.getLastName());
            studentToCreate.setNumber(studentData.getNumber());

            studentsDAO.persist(studentToCreate);
            return Response.ok().build();
        } catch (OptimisticLockException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer studentId,
            StudentDTO studentData,
            @QueryParam("ole")final Boolean invokeOLE) {
        try {
            Student existingStudent = studentsDAO.findOne(studentId);
            if (existingStudent == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingStudent.setFirstName(studentData.getFirstName());
            existingStudent.setLastName(studentData.getLastName());

            studentsDAO.update(existingStudent);
            if (invokeOLE != null && invokeOLE) {
                Thread.sleep(3000);
                studentsDAO.flush();
                studentsDAO.persist(existingStudent);
            }
            return Response.ok().build();
        } catch (OptimisticLockException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }catch (InterruptedException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
