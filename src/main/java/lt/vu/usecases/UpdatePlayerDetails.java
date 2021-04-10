package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Position;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.entities.Player;
import lt.vu.persistence.PositionsDAO;

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
public class UpdatePlayerDetails implements Serializable {

    private Player player;
    private String newPosition;

    @Inject
    private PlayersDAO playersDAO;

    @Inject
    private PositionsDAO positionsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdatePlayerDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        this.player = playersDAO.findOne(playerId);
    }

    @Transactional
    @LoggedInvocation
    public String updatePlayer() {
        try{
            playersDAO.update(this.player);
        } catch (OptimisticLockException e) {
            return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.player.getId() + "&error=optimistic-lock-exception";
        }
        return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.player.getId();
    }

    @Transactional
    @LoggedInvocation
    public String addPlayerPosition() {

        try{
            List<Position> positions = player.getPositions();
            List<String> names = positions.stream().map(Position::getName).collect(Collectors.toList());

            if(!names.contains(newPosition)){
                Position position = positionsDAO.findOneByName(newPosition);
                if(position == null){
                    position = new Position(newPosition);
                    positionsDAO.persist(position);
                }
                positions.add(position);
                player.setPositions(positions);
            }
            playersDAO.update(this.player);
        } catch (OptimisticLockException e) {
            return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.player.getId() + "&error=optimistic-lock-exception";
        }
        return "/playerDetails.xhtml?faces-redirect=true&playerId=" + this.player.getId();
    }
}
