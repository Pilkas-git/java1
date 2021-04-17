package lt.vu.persistence;

import lt.vu.entities.Subject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SubjectsDAO {

    @Inject
    private EntityManager em;

    public void persist(Subject subject){
        this.em.persist(subject);
    }

    public Subject findOne(Integer id){
        return em.find(Subject.class, id);
    }

    public Subject findOneByName(String name){
        return em.createQuery("SELECT P from Subject P WHERE P.name = :name", Subject.class)
                .setParameter("name", name).getResultList().stream().findFirst().orElse(null);
    }

    public List<Subject> loadAll() {
        return em.createNamedQuery("Subject.findAll", Subject.class).getResultList();
    }

}
