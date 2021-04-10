package lt.vu.persistence;

import lt.vu.entities.Player;
import lt.vu.entities.Position;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PositionsDAO {

    @Inject
    private EntityManager em;

    public void persist(Position position){
        this.em.persist(position);
    }

    public Position findOne(Integer id){
        return em.find(Position.class, id);
    }

    public Position findOneByName(String name){
        return em.createQuery("SELECT P from Position P WHERE P.name = :name", Position.class)
                .setParameter("name", name).getResultList().stream().findFirst().orElse(null);
    }

    public List<Position> loadAll() {
        return em.createNamedQuery("Position.findAll", Position.class).getResultList();
    }

}
