package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "select a from Player as a")
})
@Table(name = "PLAYER")
@Getter @Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "First_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "Last_name")
    private String lastName;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Column(name = "Number")
    private Integer Number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Player_position",
            joinColumns = @JoinColumn(name = "position_Id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Position> positions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Player() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(getFullName(), player.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFullName());
    }
}
