package lazy_track.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "idusers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "responsibilities")
    private int responsibilities;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "idcompanies")
    private Company company;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "e_mail")
    private String eMail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "position")
    private String position;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project", referencedColumnName = "idproject")
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
