package console;

public class MyNoFibonacciException extends RuntimeException
{
    public MyNoFibonacciException(int rowIndex, int columnIndex, int number)
    {
        super("В ячейке " + (rowIndex+1) + "," + (columnIndex+1) + " массива " + number + " не число Фибоначчи в пределах тысячи.");
    }
}
