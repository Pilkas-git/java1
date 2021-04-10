package lt.vu.usecases;

import lt.vu.entities.Player;
import lt.vu.entities.Position;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.PositionsDAO;
import lt.vu.services.PositionSuggester;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestScoped
@Named
public class SuggestPlayerPosition implements Serializable {

    @Inject
    PositionsDAO positionsDAO;

    @Inject
    PlayersDAO playersDAO;

    @Inject
    PositionSuggester positionSuggester;

    private CompletableFuture<String> suggestionGenerationTask = null;

    @LoggedInvocation
    public String suggestPosition() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer playerId = Integer.parseInt(requestParameters.get("playerId"));
        Player player = playersDAO.findOne(playerId);
        List<Position> positions = positionsDAO.loadAll();
        positions.removeAll(player.getPositions());

        suggestionGenerationTask = CompletableFuture.supplyAsync(() ->
                positionSuggester.suggestPosition(positions));

        return  "/playerDetails.xhtml?faces-redirect=false&playerId=" + playerId;
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (suggestionGenerationTask == null) {
            return null;
        }

        return "Suggested position: " + suggestionGenerationTask.get();
    }
}
