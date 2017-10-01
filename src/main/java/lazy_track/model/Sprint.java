package lazy_track.model;

import lazy_track.dao.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "sprints")
public class Sprint implements Serializable {

    @Id
    @Column(name = "idsprint")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "goal")
    private String goal;

    @Column(name = "date")
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_story", referencedColumnName = "iduser_storie")
    private UserStory userStory;

    public Sprint() {
    }

    public Sprint(String name, String goal, LocalDate date, UserStory userStory) {
        this.name = name;
        this.goal = goal;
        this.date = date;
        this.userStory = userStory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goal='" + goal + '\'' +
                ", date=" + date +
                ", userStory=" + userStory +
                '}';
    }
}
