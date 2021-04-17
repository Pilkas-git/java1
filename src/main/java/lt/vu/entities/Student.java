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
        @NamedQuery(name = "Student.findAll", query = "select a from Student as a")
})
@Table(name = "STUDENT")
@Getter @Setter
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "First_Name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "Last_Name")
    private String lastName;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Column(name = "Number")
    private Integer Number;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_Subject",
            joinColumns = @JoinColumn(name = "Subject_Id"),
            inverseJoinColumns = @JoinColumn(name = "Student_id"))
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="Group_Id")
    private Group group;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(getFullName(), student.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFullName());
    }
}
