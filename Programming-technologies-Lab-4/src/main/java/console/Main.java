package console;

import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = {0, 5, 8, 7, 9};

        swap(array, 0, 2);

        ArrayList<Integer> list = convertTo(array);

        System.out.println(list);
        System.out.println(list.getClass());

        Box<Lemon> lemonBox = new Box<>(new Lemon());

        lemonBox.addFruits(5);
        lemonBox.showBox();

        Box<Apple> appleBox = new Box<>(new Apple(), new Apple());

        appleBox.addFruits(5);
        appleBox.showBox();

        Box<Apple> appleBox2 = new Box<>(new Apple());

        appleBox.addFruits(2);

        Box<Orange> orangeBox = new Box<>(new Orange());

        System.out.println(appleBox2.compareTo(orangeBox));

        appleBox.transferFruitsTo(appleBox);
        appleBox2.showBox();
        appleBox.showBox();
    }

    public static <T> void swap(T[] array, int firstIndex, int secondIndex)
    {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static <T> ArrayList<T> convertTo(T[] array)
    {
        return new ArrayList<>(Arrays.asList(array));
    }
}

