package console;

import console.obstacles.*;
import console.participants.Cat;
import console.participants.Human;
import console.participants.IParticipant;
import console.participants.Robot;

public class Main
{
    public static void main(String[] args)
    {
        IParticipant[] participants = {
                new Robot("Робот"),
                new Robot("Робот1"),
                new Human("Человек"),
                new Human("Человек1"),
                new Cat("Кот")
        };

        IObstacle[] obstacles = {
                new Treadmill(TreadmillLength.SHORT),
                new Treadmill(TreadmillLength.LONG),
                new Treadmill(TreadmillLength.LONG),
                new Wall(WallHeight.SHORT),
                new Wall(WallHeight.HIGH),
        };

        for (IParticipant participant : participants)
        {
            for (IObstacle obstacle : obstacles)
            {
                if (obstacle.isOvercome(participant) == false)
                {
                    break;
                }
            }

            System.out.println();
        }
    }
}

