package app.model;

import java.io.Serializable;

public class Issue implements Serializable {

    private int id;
    private Sprint sprint;
    private String name;
    private User createdBy;
    private int priority;
    private int type;
    private int state;
    private int severity;
    private User sign;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (id != other.id)
            return false;
        return true;
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
