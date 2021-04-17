package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Group;
import lt.vu.mybatis.dao.UniversitygroupMapper;
import lt.vu.mybatis.model.Universitygroup;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GroupMyBatis {
    @Inject
    private UniversitygroupMapper universitygroupMapper;

    @Getter
    private List<Universitygroup> allGroups;

    @Getter @Setter
    private Universitygroup groupToCreate = new Universitygroup();

    @PostConstruct
    public void init() {
        this.loadAllGroups();
    }

    private void loadAllGroups() {
        this.allGroups = universitygroupMapper.selectAll();
    }

    @Transactional
    public String createGroup() {
        universitygroupMapper.insert(groupToCreate);
        return "/myBatis/groups?faces-redirect=true";
    }
}
