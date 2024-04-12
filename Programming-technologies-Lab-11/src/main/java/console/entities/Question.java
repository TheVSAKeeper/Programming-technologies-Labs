package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Question
{
    @OneToMany(mappedBy = "answerId")
    public List<Answer> answers;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "question_id", nullable = false)
    private int questionId;
    @Basic
    @Column(name = "question", nullable = false, length = -1)
    private String question;
    @ManyToOne
    @JoinColumn(name = "anamnesis_template_id")
    private AnamnesisTemplate anamnesisTemplateId;

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public AnamnesisTemplate getAnamnesisTemplateId()
    {
        return anamnesisTemplateId;
    }

    public void setAnamnesisTemplateId(AnamnesisTemplate anamnesisTemplateId)
    {
        this.anamnesisTemplateId = anamnesisTemplateId;
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
        Question question1 = (Question) object;
        return questionId == question1.questionId && anamnesisTemplateId == question1.anamnesisTemplateId &&
               Objects.equals(question, question1.question);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(questionId, question, anamnesisTemplateId);
    }
}
