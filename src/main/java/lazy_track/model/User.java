package lazy_track.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "idusers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "responsibilities")
    private int responsibilities;

    @Column(name = "company")
    private int company;

    @Column(name = "name")
    private String name;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @Column(name = "project")
    private int project;

    public User() {
    }

    public User(int id, String login, String password, int responsibilities, int company,
                String name, String eMail, String phone, String position, int project) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.company = company;
        this.name = name;
        this.eMail = eMail;
        this.phone = phone;
        this.position = position;
        this.project = project;
        this.responsibilities = responsibilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
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

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }
}
