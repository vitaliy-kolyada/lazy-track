package app.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Sprint implements Serializable {

    private int id;
    private String name;
    private String goal;
    private Project project;
    private LocalDate date;

    public Sprint() {
    }

    public Sprint(String name, String goal, Project project, LocalDate date) {
        this.name = name;
        this.goal = goal;
        this.project = project;
        this.date = date;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sprint sprint = (Sprint) o;
        return id == sprint.id &&
                Objects.equals(name, sprint.name) &&
                Objects.equals(goal, sprint.goal) &&
                Objects.equals(project, sprint.project) &&
                Objects.equals(date, sprint.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goal, project, date);
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goal='" + goal + '\'' +
                ", project='" + project + '\'' +
                ", date=" + date +
                '}';
    }
}
