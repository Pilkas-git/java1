package lt.vu.mybatis.model;

public class StudentSubject {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT_SUBJECT.SUBJECT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    private Integer subjectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDENT_SUBJECT.STUDENT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    private Integer studentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT_SUBJECT.SUBJECT_ID
     *
     * @return the value of PUBLIC.STUDENT_SUBJECT.SUBJECT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT_SUBJECT.SUBJECT_ID
     *
     * @param subjectId the value for PUBLIC.STUDENT_SUBJECT.SUBJECT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STUDENT_SUBJECT.STUDENT_ID
     *
     * @return the value of PUBLIC.STUDENT_SUBJECT.STUDENT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STUDENT_SUBJECT.STUDENT_ID
     *
     * @param studentId the value for PUBLIC.STUDENT_SUBJECT.STUDENT_ID
     *
     * @mbg.generated Sat Apr 17 17:20:05 EEST 2021
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}