package console.participants;

public class Cat implements IParticipant, ISuperRunnable
{
    static final int MAX_RUN_DISTANCE = 15;
    static final int MAX_JUMP_HEIGHT = 200;
    boolean isSuperRunUsed = false;
    String name;

    public Cat(String name)
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
