package console;

@Table(title = "Ant2")
public class Ant
{
   // @Column
    private String name;
    @Column
    private int lifespan;
    @Column
    private InsectColor color;

    public Ant(String name, int lifespan, InsectColor color)
    {
        this.name = name;
        this.lifespan = lifespan;
        this.color = color;
    }
}

