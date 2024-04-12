package console.participants;

public class Robot implements IParticipant, ISuperRunnable
{
    static final int MAX_RUN_DISTANCE = 50;
    static final int MAX_JUMP_HEIGHT = 100;
    boolean isSuperRunUsed = false;
    String name;

    public Robot(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public boolean canJump(int height)
    {
        return height <= MAX_JUMP_HEIGHT;
    }

    @Override
    public boolean canRun(int distance)
    {
        return distance <= MAX_RUN_DISTANCE;
    }

    @Override
    public boolean trySuperRun()
    {
        if (isSuperRunUsed)
        {
            return false;
        }

        isSuperRunUsed = true;
        return true;
    }
}

