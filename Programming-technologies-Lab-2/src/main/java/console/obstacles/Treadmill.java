package console.obstacles;

import console.participants.IParticipant;
import console.participants.ISuperRunnable;

public class Treadmill implements IObstacle
{
    TreadmillLength treadmillLength;

    public Treadmill(TreadmillLength treadmillLength)
    {
        this.treadmillLength = treadmillLength;

    }

    @Override
    public boolean isOvercome(IParticipant participant)
    {
        if (participant.canRun(treadmillLength.length))
        {
            System.out.println(participant.getName() + " успешно пробежал");
            return true;
        }
        else if (((ISuperRunnable)participant).trySuperRun())
        {
            System.out.println(participant.getName() + " использовал супер бег и пробежал");
            return true;
        }
        else
        {
            System.out.println(participant.getName() + " не пробежал");
            return false;
        }
    }
}
