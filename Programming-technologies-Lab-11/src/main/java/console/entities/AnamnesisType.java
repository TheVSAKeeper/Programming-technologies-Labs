package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "anamnesis_type", schema = "catalog", catalog = "surveys")
public class AnamnesisType
{
    @OneToMany(mappedBy = "anamnesisTemplateId")
    public List<AnamnesisTemplate> anamnesisTemplates;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "anamnesis_type_id", nullable = false)
    private short anamnesisTypeId;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public List<AnamnesisTemplate> getAnamnesisTemplates()
    {
        return anamnesisTemplates;
    }

    public void setAnamnesisTemplates(List<AnamnesisTemplate> anamnesisTemplates)
    {
        this.anamnesisTemplates = anamnesisTemplates;
    }

    public short getAnamnesisTypeId()
    {
        return anamnesisTypeId;
    }

    public void setAnamnesisTypeId(short anamnesisTypeId)
    {
        this.anamnesisTypeId = anamnesisTypeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        AnamnesisType that = (AnamnesisType) object;
        return anamnesisTypeId == that.anamnesisTypeId && Objects.equals(name, that.name) && Objects.equals(description,
                                                                                                            that.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(anamnesisTypeId, name, description);
    }
}
