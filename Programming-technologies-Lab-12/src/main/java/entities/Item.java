package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private int value;

    public Item()
    {
        value = 0;
    }

    public void increment()
    {
        value++;
    }

    public int getValue()
    {
        return value;
    }
}
