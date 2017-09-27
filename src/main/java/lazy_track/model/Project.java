package lazy_track.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @Column(name = "idproject")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "idcompanies", nullable = false, unique = true)
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Project() {
    }

    public Project(Company company, String name, String description) {
        this.company = company;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", company=" + company +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
