package console.participants;

public class Human implements IParticipant, ISuperRunnable
{
    static final int MAX_RUN_DISTANCE = 200;
    static final int MAX_JUMP_HEIGHT = 10;
    boolean isSuperRunUsed = false;
    String name;

    public Human(String name)
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
