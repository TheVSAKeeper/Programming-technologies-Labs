package console;

public class Apple extends Fruit implements IComparableFruit
{
    private static final float weight = 1.0f;

    public Apple()
    {
        super(weight);
    }
}
