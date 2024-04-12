package console.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Answer
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "answer_id", nullable = false)
    private int answerId;
    @Basic
    @Column(name = "answer", nullable = false, length = -1)
    private String answer;
    @ManyToOne
    @JoinColumn(name = "anamnesis_id")
    private Anamnesis anamnesisId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question questionId;

    public int getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(int answerId)
    {
        this.answerId = answerId;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public Anamnesis getAnamnesisId()
    {
        return anamnesisId;
    }

    public void setAnamnesisId(Anamnesis anamnesisId)
    {
        this.anamnesisId = anamnesisId;
    }

    public Question getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Question questionId)
    {
        this.questionId = questionId;
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
        Answer answer1 = (Answer) object;
        return answerId == answer1.answerId && anamnesisId == answer1.anamnesisId && questionId == answer1.questionId &&
               Objects.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(answerId, answer, anamnesisId, questionId);
    }
}
