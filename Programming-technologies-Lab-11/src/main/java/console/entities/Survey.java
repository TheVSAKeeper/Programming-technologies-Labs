package console.entities;

import jakarta.persistence.*;

import java.sql.Date;

import java.util.List;
import java.util.Objects;

@Entity
public class Survey
{
    @OneToMany(mappedBy = "surveyDiagnosisId")
    public List<SurveyDiagnosis> surveyDiagnoses;
    @OneToMany(mappedBy = "anamnesisId")
    public List<Anamnesis> anamneses;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "survey_id", nullable = false)
    private int surveyId;
    @Basic
    @Column(name = "complaint", nullable = false, length = -1)
    private String complaint;
    @Basic
    @Column(name = "date_of_survey", nullable = false)
    private Date dateOfSurvey;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public List<SurveyDiagnosis> getSurveyDiagnoses()
    {
        return surveyDiagnoses;
    }

    public void setSurveyDiagnoses(List<SurveyDiagnosis> surveyDiagnoses)
    {
        this.surveyDiagnoses = surveyDiagnoses;
    }

    public List<Anamnesis> getAnamneses()
    {
        return anamneses;
    }

    public void setAnamneses(List<Anamnesis> anamneses)
    {
        this.anamneses = anamneses;
    }

    public int getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(int surveyId)
    {
        this.surveyId = surveyId;
    }

    public String getComplaint()
    {
        return complaint;
    }

    public void setComplaint(String complaint)
    {
        this.complaint = complaint;
    }

    public Date getDateOfSurvey()
    {
        return dateOfSurvey;
    }

    public void setDateOfSurvey(Date dateOfSurvey)
    {
        this.dateOfSurvey = dateOfSurvey;
    }

    public Patient getPatient()
    {
        return patientId;
    }

    public void setPatientId(Patient patientId)
    {
        this.patientId = patientId;
    }

    public User getUserId()
    {
        return userId;
    }

    public void setUserId(User userId)
    {
        this.userId = userId;
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
        Survey survey = (Survey) object;
        return surveyId == survey.surveyId && patientId == survey.patientId && userId == survey.userId &&
               Objects.equals(complaint, survey.complaint) && Objects.equals(dateOfSurvey, survey.dateOfSurvey);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(surveyId, complaint, dateOfSurvey, patientId, userId);
    }

    @Override
    public String toString()
    {
        return userId + " " + patientId + " " + complaint + " " + dateOfSurvey;
    }
}
