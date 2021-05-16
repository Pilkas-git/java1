package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.GroupsDAO;
import lt.vu.entities.Student;
import lt.vu.entities.Group;
import lt.vu.persistence.interfaces.IStudentsDAO;

@Model
@Named
@ViewScoped
public class StudentsForGroup implements Serializable {

    @Inject
    private GroupsDAO groupsDAO;

    @Inject
    private IStudentsDAO studentsDAO;

    @Getter @Setter
    private Group group;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @Getter @Setter
    private int count;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer groupId = Integer.parseInt(requestParameters.get("groupId"));
        this.group = groupsDAO.findOne(groupId);
        this.count = group.getStudents().size();
    }

    @Transactional
    @LoggedInvocation
    public String createStudent() {
        studentToCreate.setGroup(this.group);
        studentsDAO.persist(studentToCreate);
        return "students?faces-redirect=true&groupId=" + this.group.getId();
    }
}
