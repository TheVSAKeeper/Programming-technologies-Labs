package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User
{
    @OneToMany(mappedBy = "surveyId")
    public List<Survey> surveys;
    @OneToMany(mappedBy = "surveyDiagnosisId")
    public List<SurveyDiagnosis> surveyDiagnoses;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "login", nullable = false, length = 20)
    private String login;
    @Basic
    @Column(name = "hashed_password", nullable = false, length = 255)
    private String hashedPassword;
    @Basic
    @Column(name = "salt", nullable = false, length = 1024)
    private String salt;
    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Basic
    @Column(name = "patronymic", nullable = true, length = 20)
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    public List<Survey> getSurveys()
    {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys)
    {
        this.surveys = surveys;
    }

    public List<SurveyDiagnosis> getSurveyDiagnoses()
    {
        return surveyDiagnoses;
    }

    public void setSurveyDiagnoses(List<SurveyDiagnosis> surveyDiagnoses)
    {
        this.surveyDiagnoses = surveyDiagnoses;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getPatronymic()
    {
        return patronymic;
    }

    public void setPatronymic(String patronymic)
    {
        this.patronymic = patronymic;
    }

    public Role getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Role roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (object == null || getClass() != object.getClass())
        {
            return false;
        }
        User user = (User) object;
        return userId == user.userId && roleId == user.roleId && Objects.equals(login, user.login) && Objects.equals(
                hashedPassword,
                user.hashedPassword) && Objects.equals(salt, user.salt) && Objects.equals(lastName, user.lastName) &&
               Objects.equals(firstName, user.firstName) && Objects.equals(patronymic, user.patronymic);
    }

    @Override
    public String toString()
    {
        return lastName + " " + firstName + " " + patronymic + " " + roleId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, login, hashedPassword, salt, lastName, firstName, patronymic, roleId);
    }
}
