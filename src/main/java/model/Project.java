package model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "idproject")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, Company company, String name, String description) {
        this.id = id;
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
}
