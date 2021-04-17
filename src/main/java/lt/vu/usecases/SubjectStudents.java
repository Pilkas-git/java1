package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Subject;
import lt.vu.persistence.SubjectsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class SubjectStudents implements Serializable {

    @Inject
    private SubjectsDAO subjectsDAO;

    @Getter @Setter
    private Subject subject;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer subjectId = Integer.parseInt(requestParameters.get("subjectId"));
        this.subject = subjectsDAO.findOne(subjectId);
    }
}
