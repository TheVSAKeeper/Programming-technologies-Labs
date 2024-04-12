package console.obstacles;

import console.participants.IParticipant;

public class Wall implements IObstacle
{
    WallHeight wallHeight;

    public Wall(WallHeight wallHeight)
    {
        this.wallHeight = wallHeight;
    }

    @Override
    public boolean isOvercome(IParticipant participant)
    {
        if (participant.canJump(wallHeight.height))
        {
            System.out.println(participant.getName() + " успешно прыгнул");
            return true;
        }
        else
        {
            System.out.println(participant.getName() + " не прыгнул");
            return false;
        }
    }
}
