package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "anamnesis_template", schema = "catalog", catalog = "surveys")
public class AnamnesisTemplate
{
    @OneToMany(mappedBy = "anamnesisId")
    public List<Anamnesis> anamneses;
    @OneToMany(mappedBy = "questionId")
    public List<Question> questions;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "anamnesis_template_id", nullable = false)
    private short anamnesisTemplateId;
    @Basic
    @Column(name = "anamnesis_type_id", nullable = false)
    private short anamnesisTypeId;

    public List<Anamnesis> getAnamneses()
    {
        return anamneses;
    }

    public void setAnamneses(List<Anamnesis> anamneses)
    {
        this.anamneses = anamneses;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }

    public short getAnamnesisTemplateId()
    {
        return anamnesisTemplateId;
    }

    public void setAnamnesisTemplateId(short anamnesisTemplateId)
    {
        this.anamnesisTemplateId = anamnesisTemplateId;
    }

    public short getAnamnesisTypeId()
    {
        return anamnesisTypeId;
    }

    public void setAnamnesisTypeId(short anamnesisTypeId)
    {
        this.anamnesisTypeId = anamnesisTypeId;
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
        AnamnesisTemplate that = (AnamnesisTemplate) object;
        return anamnesisTemplateId == that.anamnesisTemplateId && anamnesisTypeId == that.anamnesisTypeId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(anamnesisTemplateId, anamnesisTypeId);
    }
}
