package console.obstacles;

public enum WallHeight
{
    SHORT(20),
    HIGH(110);
    final int height;

    WallHeight(int height)
    {
        this.height = height;
    }
}
