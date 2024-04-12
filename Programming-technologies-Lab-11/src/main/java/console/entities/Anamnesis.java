package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Anamnesis
{
    @OneToMany(mappedBy = "answerId")
    public List<Answer> answers;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "anamnesis_id", nullable = false)
    private int anamnesisId;
    @ManyToOne
    @JoinColumn(name = "anamnesis_template_id")
    private AnamnesisTemplate anamnesisTemplate;
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey surveyId;

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }

    public int getAnamnesisId()
    {
        return anamnesisId;
    }

    public void setAnamnesisId(int anamnesisId)
    {
        this.anamnesisId = anamnesisId;
    }

    public AnamnesisTemplate getAnamnesisTemplate()
    {
        return anamnesisTemplate;
    }

    public void setAnamnesisTemplate(AnamnesisTemplate anamnesisTemplateId)
    {
        this.anamnesisTemplate = anamnesisTemplateId;
    }

    public Survey getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId)
    {
        this.surveyId = surveyId;
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
        Anamnesis anamnesis = (Anamnesis) object;
        return anamnesisId == anamnesis.anamnesisId && anamnesisTemplate == anamnesis.anamnesisTemplate &&
               surveyId == anamnesis.surveyId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(anamnesisId, anamnesisTemplate, surveyId);
    }
}
