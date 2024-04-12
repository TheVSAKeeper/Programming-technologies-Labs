package console;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main
{
    private static final PostgresDB postgresDB = new PostgresDB();

    public static void main(String[] args) throws SQLException
    {
        deleteTables();
        createTables();
        fillTables();
        System.out.println();

        showStudentsPassed("Математика");
        showAvgSubjectMark("Управление данными");
        showAvgStudentMark("Василиса");
        showMostPassedSubject(3);
        showGoodStudent();

        postgresDB.disconnect();
    }

    private static void deleteTables() throws SQLException
    {
        postgresDB.statement.executeUpdate(
                "DROP TABLE IF EXISTS public.progress CASCADE;\n" + "DROP TABLE IF EXISTS public.student CASCADE;\n" +
                "DROP TABLE IF EXISTS public.subject CASCADE;");

        System.out.println("Tables deleted");
    }

    private static void createTables() throws SQLException
    {
        postgresDB.statement.execute(
                "CREATE TABLE student\n" + "(\n" + "    student_id      SERIAL NOT NULL PRIMARY KEY,\n" +
                "    name            TEXT,\n" + "    passport_series VARCHAR(4),\n" +
                "    passport_number VARCHAR(6)\n" + ");\n" + "\n" + "CREATE TABLE subject\n" + "(\n" +
                "    subject_id SERIAL NOT NULL PRIMARY KEY,\n" + "    name       TEXT\n" + ");\n" + "\n" +
                "CREATE TABLE progress\n" + "(\n" + "    id         SERIAL NOT NULL PRIMARY KEY,\n" +
                "    mark       INT,\n" + "    student_id INT,\n" + "    subject_id INT,\n" + "\n" +
                "    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student (student_id)\n" +
                "        ON DELETE CASCADE,\n" +
                "    CONSTRAINT subject_id_fk FOREIGN KEY (subject_id) REFERENCES subject (subject_id)\n" +
                "        ON DELETE CASCADE\n" + "); ALTER TABLE student\n" +
                "    ADD CONSTRAINT series_number_unique UNIQUE (passport_series, passport_number);\n" + "\n" +
                "ALTER TABLE progress\n" + "    ADD CONSTRAINT mark_check CHECK (mark BETWEEN 2 AND 5);");

        System.out.println("Tables created");
    }

    private static void fillTables() throws SQLException
    {
        int result = postgresDB.statement.executeUpdate(
                "INSERT INTO student (name, passport_series, passport_number)\n" +
                "VALUES ('Василиса', '4631', '516097'),\n" + "       ('Антон', '4147', '126650'),\n" +
                "       ('Ефим', '4130', '157955'),\n" + "       ('Максим', '4489', '913047');\n" + "\n" +
                "INSERT INTO subject (name)\n" + "VALUES ('Математика'),\n" +
                "       ('Технологии программирования'),\n" + "       ('Алгоритмы и структуры данных'),\n" +
                "       ('Управление данными');\n" + "\n" + "INSERT INTO progress (mark, student_id, subject_id)\n" +
                "VALUES (5, 1, 1),\n" + "       (2, 1, 4),\n" + "       (3, 1, 3),\n" + "       (5, 2, 1),\n" +
                "       (2, 2, 3),\n" + "       (2, 3, 4),\n" + "       (4, 4, 1), \n" +
                "       (5, 1, 4); INSERT INTO progress (mark, student_id, subject_id)\n" + "VALUES (5, 4, 2),\n" +
                "       (5, 4, 3),\n" + "       (5, 4, 4);");

        System.out.println("Updated " + result + " tables");
    }

    private static void showStudentsPassed(String subject) throws SQLException
    {
        PreparedStatement statement = postgresDB.connection.prepareStatement(
                "SELECT s.name, p.mark\n" + "FROM progress p\n" +
                "         JOIN student s ON s.student_id = p.student_id\n" +
                "         JOIN subject sb ON sb.subject_id = p.subject_id\n" + "WHERE sb.name = ? AND p.mark > 3;");
        statement.setString(1, subject);

        System.out.println(subject);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            String studentName = resultSet.getString(1);
            int mark = resultSet.getInt(2);

            System.out.printf("%s (%d) \n", studentName, mark);
        }

        System.out.println();
    }

    private static void showAvgSubjectMark(String subject) throws SQLException
    {
        PreparedStatement statement = postgresDB.connection.prepareStatement(
                "SELECT avg(mark)\n" + "FROM progress p\n" +
                "         JOIN subject sb ON sb.subject_id = p.subject_id\n" + "WHERE sb.name = ?;");
        statement.setString(1, subject);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            double avg = resultSet.getDouble(1);

            System.out.println(subject + " - " + avg);
        }

        System.out.println();
    }

    private static void showAvgStudentMark(String name) throws SQLException
    {
        PreparedStatement statement = postgresDB.connection.prepareStatement(
                "SELECT avg(mark)\n" + "FROM progress p\n" +
                "         JOIN subject sb ON sb.subject_id = p.subject_id\n" +
                "         JOIN student s ON s.student_id = p.student_id\n" + "WHERE s.name = ?;");
        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            double avg = resultSet.getDouble(1);

            System.out.println(name + " - " + avg);
        }

        System.out.println();
    }

    private static void showMostPassedSubject(int limit) throws SQLException
    {
        PreparedStatement statement = postgresDB.connection.prepareStatement(
                "SELECT sb.name, count(*)\n" + "FROM progress p\n" +
                "         JOIN subject sb ON sb.subject_id = p.subject_id\n" + "WHERE mark >= 3\n" +
                "GROUP BY sb.name\n" + "ORDER BY count(*) DESC\n" + "LIMIT ?;");
        statement.setInt(1, limit);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            String subjectName = resultSet.getString(1);
            int passedCount = resultSet.getInt(2);

            System.out.printf("%s (%d) \n", subjectName, passedCount);
        }

        System.out.println();
    }

    private static void showGoodStudent() throws SQLException
    {
        ResultSet resultSet = postgresDB.statement.executeQuery("SELECT DISTINCT s.name\n" + "FROM progress p\n" +
                                                                "         JOIN student s ON s.student_id = p.student_id\n" +
                                                                "WHERE NOT exists(SELECT *\n" +
                                                                "                 FROM progress p2\n" +
                                                                "                 WHERE p2.student_id = p.student_id AND p2.mark <= 3);");
        while (resultSet.next())
        {
            String studentName = resultSet.getString(1);
            System.out.println(studentName);
        }
    }
}
