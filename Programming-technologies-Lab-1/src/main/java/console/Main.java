package console;

import console.animals.Animal;
import console.animals.Cat;
import console.animals.Dog;
import console.animals.Tiger;
import console.animals.insects.Ant;
import console.animals.insects.Butterfly;
import console.animals.insects.Dragonfly;
import console.animals.insects.Insect;

public class Main
{
    public static void main(String[] args)
    {
        int runDistance = 300;
        int swimDistance = 10;

        Animal[] animals = {new Cat("Хемуль"),
                new Cat("Альпен"),
                new Cat("Шумми"),
                new Dog("Олтар"),
                new Tiger("Кларк"),
                new Tiger("Нубис"),
                new Ant("Муравей"),
                new Butterfly("Бабочка"),
                new Dragonfly("Стрекоза"),};

        for (Animal animal : animals)
        {
            animal.run(runDistance);
            animal.swim(swimDistance);

            if (animal instanceof Insect)
            {
                ((Insect) animal).showLifespan();
            }
        }

        System.out.println("Animals: " + Animal.getCount() + "\nCats: " + Cat.getCount() + "\nDogs: " + Dog.getCount() + "\nTiger: " + Tiger.getCount());
    }
}
