package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class PlayerPositions implements Serializable {

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Player player;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        this.player = playersDAO.findOne(playerId);
    }
}
