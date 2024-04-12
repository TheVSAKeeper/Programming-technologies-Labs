package console.animals;

public class Tiger extends Animal
{
    private static final int maxRunDistance = 1000;
    private static final int maxSwimDistance = 50;
    private static int count;

    public Tiger(String name)
    {
        super(name, maxRunDistance, maxSwimDistance);
        count++;
    }

    public static int getCount()
    {
        return count;
    }
}
