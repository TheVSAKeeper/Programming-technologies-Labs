package console.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "survey_diagnosis", schema = "catalog", catalog = "surveys")
public class SurveyDiagnosis
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "survey_diagnosis_id", nullable = false)
    private int surveyDiagnosisId;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey surveyId;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosisId;
    @Basic
    @Column(name = "reason", nullable = false, length = -1)
    private String reason;
    @Basic
    @Column(name = "date_of_diagnosis", nullable = false)
    private Date dateOfDiagnosis;

    public int getSurveyDiagnosisId()
    {
        return surveyDiagnosisId;
    }

    public void setSurveyDiagnosisId(int surveyDiagnosisId)
    {
        this.surveyDiagnosisId = surveyDiagnosisId;
    }

    public Survey getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId)
    {
        this.surveyId = surveyId;
    }

    public Diagnosis getDiagnosisId()
    {
        return diagnosisId;
    }

    public void setDiagnosisId(Diagnosis diagnosisId)
    {
        this.diagnosisId = diagnosisId;
    }

    public Patient getPatientId()
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

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public Date getDateOfDiagnosis()
    {
        return dateOfDiagnosis;
    }

    public void setDateOfDiagnosis(Date dateOfDiagnosis)
    {
        this.dateOfDiagnosis = dateOfDiagnosis;
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
        SurveyDiagnosis that = (SurveyDiagnosis) object;
        return surveyDiagnosisId == that.surveyDiagnosisId && surveyId == that.surveyId &&
               diagnosisId == that.diagnosisId && patientId == that.patientId && userId == that.userId &&
               Objects.equals(reason, that.reason) && Objects.equals(dateOfDiagnosis, that.dateOfDiagnosis);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(surveyDiagnosisId, surveyId, diagnosisId, patientId, userId, reason, dateOfDiagnosis);
    }

    @Override
    public String toString()
    {
        return patientId + " " + dateOfDiagnosis.toString() + " " + diagnosisId.getDiagnosis();
    }
}
