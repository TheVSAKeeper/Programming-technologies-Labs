package console;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args)
    {
        connect();

        Ant ant1 = new Ant("name1", 100, InsectColor.BLACK);
        Ant ant2 = new Ant("name2", 10, InsectColor.WHITE);

        addObject(ant1);
        addObject(ant2);

        disconnect();
    }

    private static void connect()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_pt", "postgres", "");
            statement = connection.createStatement();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void disconnect()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        try
        {
            statement.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static void createTable(Object object)
    {
        var mappedClass = object.getClass();

        if (mappedClass.isAnnotationPresent(Table.class) == false)
        {
            System.out.println("Отсутствует название таблицы");
            return;
        }

        String tableName = mappedClass.getAnnotation(Table.class).title();

        String prefix = MessageFormat.format("CREATE TABLE {0}\n(\n", tableName);

        String sql = Arrays
                .stream(mappedClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getName() + " " + getSqlTypeName(field.getType()))
                .collect(Collectors.joining(",\n", prefix, "\n);"));

        if (sql.isBlank())
        {
            System.out.println("Таблица не содержит столбцов");
            return;
        }

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void addObject(Object object)
    {
        Class<?> mappedClass = object.getClass();

        if (mappedClass.isAnnotationPresent(Table.class) == false)
        {
            System.out.println("Отсутствует название таблицы");
            return;
        }

        String tableName = mappedClass.getAnnotation(Table.class).title();

        String prefix = MessageFormat.format("INSERT INTO {0}{1}", tableName, " (");

        List<Field> columns = Arrays.stream(mappedClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(
                Column.class)).toList();

        String sqlPrefix = columns.stream().map(Field::getName).collect(Collectors.joining(", ", prefix, ")"));

        String sqlSuffix = columns.stream().map(field ->
                                                {
                                                    try
                                                    {
                                                        field.setAccessible(true);
                                                        Object value = field.get(object);
                                                        return getSqlType(field, value);
                                                    }
                                                    catch (IllegalAccessException e)
                                                    {
                                                        throw new RuntimeException(e);
                                                    }
                                                }).collect(Collectors.joining(", ", " VALUES (", ");"));

        String sql = sqlPrefix + sqlSuffix;

        try
        {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            try
            {
                Statement statement = connection.createStatement();
                statement.execute("drop table if exists " + tableName + ";");
                createTable(object);
                statement.execute(sql);
            }
            catch (SQLException ex)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    private static String getSqlType(Field field, Object value)
    {
        return switch (field.getType().getName())
        {
            case "int", "java.lang.Integer", "boolean", "java.lang.Boolean", "float", "java.lang.Float", "double", "java.lang.Double" ->
                    value.toString();
            default -> "'" + value + "'";
        };
    }

    private static String getSqlTypeName(Class<?> type)
    {
        return switch (type.getName())
        {
            case "int", "java.lang.Integer" -> "INTEGER";
            case "float", "java.lang.Float", "double", "java.lang.Double" -> "REAL";
            case "boolean", "java.lang.Boolean" -> "BLOB";
            default -> "TEXT";
        };
    }
}
