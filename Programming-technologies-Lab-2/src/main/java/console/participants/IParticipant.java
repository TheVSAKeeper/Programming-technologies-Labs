package console.participants;

public interface IParticipant
{
    String getName();

    boolean canJump(int height);

    boolean canRun(int distance);
}
