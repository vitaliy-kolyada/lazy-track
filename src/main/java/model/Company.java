package model;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "idcompanies")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Company() {
    }

    public Company(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Company(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
