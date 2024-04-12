package console.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Role
{
    @OneToMany(mappedBy = "userId")
    public List<User> users;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id", nullable = false)
    private short roleId;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public List<User> getRoles()
    {
        return users;
    }

    public void setRoles(List<User> users)
    {
        this.users = users;
    }

    public short getRoleId()
    {
        return roleId;
    }

    public void setRoleId(short roleId)
    {
        this.roleId = roleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        Role role = (Role) object;
        return roleId == role.roleId && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(roleId, name);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
