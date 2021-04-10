package lt.vu.services;

import lt.vu.entities.Position;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class PositionSuggester implements Serializable {

    public String suggestPosition(List<Position> positions) {
        Random rand = new Random();
        if(positions.isEmpty()) {
            return "No possitions available";
        }
        return positions.get(rand.nextInt(positions.size())).getName();
    }
}