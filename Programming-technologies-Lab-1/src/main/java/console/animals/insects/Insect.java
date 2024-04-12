package console.animals.insects;

import console.animals.Animal;

public abstract class Insect extends Animal
{
    private final int lifespan;

    public Insect(String name, int maxRunDistance, int lifespan)
    {
        super(name, maxRunDistance, 0);
        this.lifespan = lifespan;
    }

    public void showLifespan()
    {
        System.out.println("Продолжительность жизни у " + super.getName() + " " + lifespan + " лет");
    }
}
