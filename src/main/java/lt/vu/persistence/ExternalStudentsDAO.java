package lt.vu.persistence;

import lt.vu.entities.Student;
import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
@Specializes
@LoggedInvocation
public class ExternalStudentsDAO extends StudentsDAO{

    @Inject
    private EntityManager em;

    public void persist(Student student){
        student.setNumber(1110000);
        this.em.persist(student);
    }
}
