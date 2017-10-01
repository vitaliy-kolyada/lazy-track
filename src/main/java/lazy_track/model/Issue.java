package lazy_track.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "issues")
public class Issue implements Serializable {

    @Id
    @Column(name = "idissue")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint", referencedColumnName = "idsprint")
    private Sprint sprint;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "idusers")
    private User createdBy;

    @Column(name = "priority")
    private int priority;

    @Column(name = "type")
    private int type;

    @Column(name = "state")
    private int state;

    @Column(name = "severity")
    private int severity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sign", referencedColumnName = "idusers")
    private User sign;

    @Column(name = "story_points")
    private int storyPoints;


    public Issue() {
    }

    public Issue(Sprint sprint, String name, User createdBy, int priority, int type, int state, int severity, User sign, int storyPoints) {
        this.sprint = sprint;
        this.name = name;
        this.createdBy = createdBy;
        this.priority = priority;
        this.type = type;
        this.state = state;
        this.severity = severity;
        this.sign = sign;
        this.storyPoints = storyPoints;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getSign() {
        return sign;
    }

    public void setSign(User sign) {
        this.sign = sign;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", sprint=" + sprint +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", priority=" + priority +
                ", type=" + type +
                ", state=" + state +
                ", severity=" + severity +
                ", sign=" + sign +
                ", storyPoints=" + storyPoints +
                '}';
    }
}
