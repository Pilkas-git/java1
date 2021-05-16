package lt.vu.persistence.interfaces;

import lt.vu.entities.Student;

import java.util.List;

public interface IStudentsDAO {

    void persist(Student student);

    Student findOne(Integer id);

    Student update(Student student);

    List<Student> getAllStudents();

    void flush();
}
