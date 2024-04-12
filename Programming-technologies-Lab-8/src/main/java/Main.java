import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Main
{
    public static void main(String[] args)
    {
        String[] randomWords = getRandomWords();

        String mostFrequentWords = Arrays
                .stream(randomWords)
                .collect(groupingBy(word -> word, counting()))
                .entrySet()
                .stream()
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toCollection(ArrayList::new))))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(x -> x.getValue().stream().sorted((o1, o2) -> o2.compareToIgnoreCase(o1)).collect(joining(", ")) +
                          " / кол-во = " + x.getKey())
                .orElse("");

        System.out.println(mostFrequentWords);

        Employee[] employees = getEmployees();

        Employee.showHighlyPaid(employees, Employee.Position.ENGINEER, 2);
    }

    private static String[] getRandomWords()
    {
        return new String[]{
                "яблоко",
                "массив",
                "массив",
                "массив",
                "список",
                "список",
                "запись",
                "запись",
                "запись",
                "запись",
                "запись",
                "запись",
                "массив",
                "массив",
                "список",
                "яблоко",
                "яблоко",
                "яблоко",
                "яблоко"
        };
    }

    private static Employee[] getEmployees()
    {
        return new Employee[]{
                new Employee("Бронислав", 18, Employee.Position.DIRECTOR, 10_000),
                new Employee("Ириней", 56, Employee.Position.ENGINEER, 23_000),
                new Employee("Геласий", 30, Employee.Position.ENGINEER, 100_000),
                new Employee("A", 30, Employee.Position.ENGINEER, 120_000),
                new Employee("Геннадий", 28, Employee.Position.ENGINEER, 13_000),
                new Employee("Константин", 41, Employee.Position.MANAGER, 110_000),
                new Employee("Серафим", 44, Employee.Position.MANAGER, 18_000)
        };
    }

}

class Employee
{
    private final String name;
    private final int age;
    private final Position position;
    private final int salary;

    public Employee(String name, int age, Position position, int salary)
    {
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public static void showHighlyPaid(Employee[] employees, Position position, int count)
    {
        if (count > employees.length)
        {
            count = employees.length;
        }

        String highlyPaidEmployees = Arrays
                .stream(employees)
                .filter(employee -> employee.getPosition() == position)
                .sorted(Comparator.comparing(employee -> -employee.getSalary()))
                .limit(count)
                .sorted(Comparator.comparing(employee -> employee.getName().length()))
                .map(employee -> employee.getName() + " (" + employee.getName().length() + "/" + employee.getSalary() +
                                 ")")
                .collect(joining(", ", (count + " самых высокооплачиваемых сотрудников должности " + position +
                                        " зовут: "), ";"));

        System.out.println(highlyPaidEmployees);

    }

    public int getSalary()
    {
        return salary;
    }

    public Position getPosition()
    {
        return position;
    }

    public String getName()
    {
        return name;
    }

    enum Position
    {
        ENGINEER,
        DIRECTOR,
        MANAGER
    }
}
