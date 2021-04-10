package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Position;
import lt.vu.persistence.PositionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class PositionPlayers implements Serializable {

    @Inject
    private PositionsDAO positionsDAO;

    @Getter @Setter
    private Position position;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("positionId"));
        this.position = positionsDAO.findOne(playerId);
    }
}
