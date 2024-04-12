package console.animals;

public class Dog extends Animal
{
    private static final int maxRunDistance = 500;
    private static final int maxSwimDistance = 10;
    private static int count;

    public Dog(String name)
    {
        super(name, maxRunDistance, maxSwimDistance);
        count++;
    }

    public static int getCount()
    {
        return count;
    }
}
