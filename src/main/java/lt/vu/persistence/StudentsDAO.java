package lt.vu.persistence;

import lt.vu.entities.Student;
import lt.vu.persistence.interfaces.IStudentsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@Default
public class StudentsDAO implements IStudentsDAO {

    @Inject
    private EntityManager em;

    public void persist(Student student){
        this.em.persist(student);
    }

    public List<Student> getAllStudents(){
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public Student findOne(Integer id){
        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }

    public void flush() { em.flush();}
}
