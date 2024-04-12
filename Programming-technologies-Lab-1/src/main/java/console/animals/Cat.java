package console.animals;

public class Cat extends Animal
{
    private static final int maxRunDistance = 200;
    private static int count;

    public Cat(String name)
    {
        super(name, maxRunDistance, 0);
        count++;
    }

    public static int getCount()
    {
        return count;
    }
}

