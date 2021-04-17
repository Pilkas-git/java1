package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Student;
import lt.vu.persistence.StudentsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class StudentSubjects implements Serializable {

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Student student;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }
}
