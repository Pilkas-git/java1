package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.StudentSubject;
import org.mybatis.cdi.Mapper;

@Mapper
public interface StudentSubjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT_SUBJECT
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    int insert(StudentSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.STUDENT_SUBJECT
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    List<StudentSubject> selectAll();
}