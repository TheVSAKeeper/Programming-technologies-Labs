package console;

import java.util.ArrayList;

public class Box<T extends Fruit>
{
    ArrayList<T> fruits = new ArrayList<>();

    public Box(T... fruits)
    {
        for (T fruit : fruits)
        {
            addFruit(fruit);
        }
    }

    public void addFruit(T fruit)
    {
        fruits.add(fruit);
    }

    public void addFruits(int count)
    {
        if (fruits.isEmpty())
        {
            return;
        }

        for (int i = 0; i < count; i++)
        {
            try
            {
                addFruit((T) fruits.get(0).getClass().newInstance());
            }
            catch (InstantiationException | IllegalAccessException ignored)
            {
            }
        }
    }

    public float getWeight()
    {
        if (fruits.isEmpty())
        {
            return 0.0f;
        }

        float weight = 0.0f;

        for (T fruit : fruits)
        {
            weight += fruit.getWeight();
        }

        return weight;
    }

    public boolean compareTo(Box<? extends IComparableFruit> otherBox)
    {
        return Math.abs(this.getWeight() - otherBox.getWeight()) < 0.0001;
    }

    public void transferFruitsTo(Box<T> otherBox)
    {
        if (this == otherBox)
        {
            return;
        }

        for (var fruit : otherBox.getFruits())
        {
            addFruit(fruit);
        }

        this.fruits.clear();
    }

    public ArrayList<T> getFruits()
    {
        return new ArrayList<>(fruits);
    }

    public void showBox()
    {
        System.out.print("В коробке лежат: ");

        for (var fruit : fruits)
        {
            System.out.print(fruit + " ");
        }

        System.out.println();
    }
}

