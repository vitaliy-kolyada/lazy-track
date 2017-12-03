package app.model;

import java.io.Serializable;

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
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
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
