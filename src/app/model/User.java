package app.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private long id;
    private int responsibilities;
    private Company company;
    private String login;
    private String password;
    private String name;
    private String eMail;
    private String phone;
    private String position;
    private Project project;

    public User() {
    }

    public User(int responsibilities, Company company, String login, String password, String name, String eMail, String phone, String position, Project project) {
        this.responsibilities = responsibilities;
        this.company = company;
        this.login = login;
        this.password = password;
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
        this.position = position;
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(int responsibilities) {
        this.responsibilities = responsibilities;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                responsibilities == user.responsibilities &&
                Objects.equals(company, user.company) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(eMail, user.eMail) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(position, user.position) &&
                Objects.equals(project, user.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responsibilities, company, login, password, name, eMail, phone, position, project);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", responsibilities=" + responsibilities +
                ", company=" + company +
                ", name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", project=" + project +
                '}';

    }
}
