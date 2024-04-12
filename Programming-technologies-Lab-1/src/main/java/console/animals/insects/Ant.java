package console.animals.insects;

public class Ant extends Insect
{
    private static final int maxRunDistance = 10;
    private static final int lifespan = 1;

    public Ant(String name)
    {
        super(name, maxRunDistance, lifespan);
    }
}
