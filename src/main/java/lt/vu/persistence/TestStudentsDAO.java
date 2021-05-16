package lt.vu.persistence;

import lt.vu.entities.Student;
import lt.vu.persistence.interfaces.IStudentsDAO;

import java.util.ArrayList;
import java.util.List;

public class TestStudentsDAO implements IStudentsDAO {

    @Override
    public void persist(Student student){
        return;
    }

    @Override
    public List<Student> getAllStudents(){
        List<Student> result = new ArrayList<>();
        Student student = new Student();
        student.setFirstName("Testas");
        student.setLastName("Testauskas");
        result.add(student);
        return result;
    }

    @Override
    public void flush() {
        return;
    }

    @Override
    public Student findOne(Integer id){
        if (id == 2) return null;
        Student student = new Student();
        student.setFirstName("Testas");
        student.setLastName("Testauskas");
        return student;
    }

    @Override
    public Student update(Student student){
        return student;
    }

}
