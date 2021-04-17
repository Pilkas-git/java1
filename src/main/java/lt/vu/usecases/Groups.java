package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.GroupsDAO;
import lt.vu.entities.Group;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Groups {

    @Inject
    private GroupsDAO groupsDAO;

    @Getter @Setter
    private Group groupToCreate = new Group();

    @Getter
    private List<Group> allGroups;

    @PostConstruct
    public void init(){
        loadAllGroups();
    }

    @Transactional
    public String createGroup(){
        this.groupsDAO.persist(groupToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllGroups(){
        this.allGroups = groupsDAO.loadAll();
    }
}
