package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Diagnosis
{
    @OneToMany(mappedBy = "surveyDiagnosisId")
    public List<SurveyDiagnosis> surveyDiagnoses;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "diagnosis_id", nullable = false)
    private int diagnosisId;
    @Basic
    @Column(name = "diagnosis", nullable = false, length = 40)
    private String diagnosis;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public List<SurveyDiagnosis> getSurveyDiagnoses()
    {
        return surveyDiagnoses;
    }

    public void setSurveyDiagnoses(List<SurveyDiagnosis> surveyDiagnoses)
    {
        this.surveyDiagnoses = surveyDiagnoses;
    }

    public int getDiagnosisId()
    {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId)
    {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosis()
    {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis)
    {
        this.diagnosis = diagnosis;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
        Diagnosis diagnosis1 = (Diagnosis) object;
        return diagnosisId == diagnosis1.diagnosisId && Objects.equals(diagnosis, diagnosis1.diagnosis) &&
               Objects.equals(description, diagnosis1.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(diagnosisId, diagnosis, description);
    }
}
