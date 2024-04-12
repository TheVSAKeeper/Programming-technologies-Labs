package console.entities;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Patient
{
    @OneToMany(mappedBy = "surveyId")
    public List<Survey> surveys;
    @OneToMany(mappedBy = "surveyDiagnosisId")
    public List<SurveyDiagnosis> surveyDiagnoses;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "patient_id", nullable = false)
    private int patientId;
    @Basic
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Basic
    @Column(name = "patronymic", nullable = true, length = 20)
    private String patronymic;
    @Basic
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    public Patient()
    {
    }

    public Patient(String lastName, String firstName, String patronymic, Date birthDate)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

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

    public int getPatientId()
    {
        return patientId;
    }

    public void setPatientId(int patientId)
    {
        this.patientId = patientId;
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

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public String toString()
    {
        return lastName + " " + firstName + " " + patronymic;
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
        Patient patient = (Patient) object;
        return patientId == patient.patientId && Objects.equals(lastName, patient.lastName) && Objects.equals(firstName,
                                                                                                              patient.firstName) &&
               Objects.equals(patronymic, patient.patronymic) && Objects.equals(birthDate, patient.birthDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(patientId, lastName, firstName, patronymic, birthDate);
    }
}
