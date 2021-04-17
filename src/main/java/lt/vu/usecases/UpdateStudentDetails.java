package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Subject;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.StudentsDAO;
import lt.vu.entities.Student;
import lt.vu.persistence.SubjectsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ViewScoped
@Named
@Getter @Setter
public class UpdateStudentDetails implements Serializable {

    private Student student;
    private String newSubject;

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private SubjectsDAO subjectsDAO;

    @PostConstruct
    private void init() {
        System.out.println("student details update init");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String updateStudent() {
        try{
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId();
    }

    @Transactional
    @LoggedInvocation
    public String addStudentSubject() {

        try{
            List<Subject> subjects = student.getSubjects();
            List<String> names = subjects.stream().map(Subject::getName).collect(Collectors.toList());

            if(!names.contains(newSubject)){
                Subject subject = subjectsDAO.findOneByName(newSubject);
                if(subject == null){
                    subject = new Subject(newSubject);
                    subjectsDAO.persist(subject);
                }
                subjects.add(subject);
                student.setSubjects(subjects);
            }
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId();
    }
}
