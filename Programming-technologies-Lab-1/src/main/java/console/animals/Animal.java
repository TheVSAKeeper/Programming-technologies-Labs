package console.animals;

public abstract class Animal
{
    private static int count;
    private final String name;
    private final int maxRunDistance;
    private final int maxSwimDistance;

    public Animal(String name, int maxRunDistance, int maxSwimDistance)
    {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;

        count++;
    }

    public static int getCount()
    {
        return count;
    }

    public void run(int distance)
    {
        if (maxRunDistance == 0)
        {
            System.out.println(name + " не умеет бегать");
        }
        else if (distance > maxRunDistance)
        {
            System.out.println(name + " не смог пробежать " + distance + " м");
        }
        else
        {
            System.out.println(name + " пробежал " + distance + " м");
        }
    }

    public void swim(int distance)
    {
        if (maxSwimDistance == 0)
        {
            System.out.println(name + " не умеет плавать");
        }
        else if (distance > maxSwimDistance)
        {
            System.out.println(name + " не смог проплыть " + distance + " м");
        }
        else
        {
            System.out.println(name + " проплыл " + distance + " м");
        }
    }

    public String getName()
    {
        return name;
    }
}
