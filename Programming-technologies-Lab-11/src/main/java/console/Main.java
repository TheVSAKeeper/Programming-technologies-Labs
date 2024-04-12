package console;

import console.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Anamnesis.class)
            .addAnnotatedClass(AnamnesisTemplate.class)
            .addAnnotatedClass(AnamnesisType.class)
            .addAnnotatedClass(Answer.class)
            .addAnnotatedClass(Diagnosis.class)
            .addAnnotatedClass(Patient.class)
            .addAnnotatedClass(Question.class)
            .addAnnotatedClass(Role.class)
            .addAnnotatedClass(Survey.class)
            .addAnnotatedClass(SurveyDiagnosis.class)
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        boolean isContinue = true;

        while (isContinue)
        {
            System.out.println("\nEnter command");
            String input = scanner.nextLine();

            String[] arguments = Arrays.stream(input.split(" ")).map(String::trim).filter(s -> !s.isEmpty()).toArray(
                    String[]::new);

            if (arguments.length < 1)
            {
                System.out.println("Command not entered");
                continue;
            }
            String command = arguments[0];

            final String getSurveysByPatientCommand = "/getSurveysByPatient";
            final String getDiagnosisByUserCommand = "/getDiagnosisByUser";
            final String updateUserLoginCommand = "/updateUserLogin";
            final String deletePatientCommand = "/removePatient";
            final String addPatientCommand = "/addPatient";
            final String exitCommand = "/exit";

            switch (command)
            {
                case (getSurveysByPatientCommand) ->
                {
                    String patientFirstName = arguments[1];
                    getSurveysByPatient(patientFirstName).forEach(System.out::println);
                }
                case (getDiagnosisByUserCommand) ->
                {
                    String userLastName = arguments[1];
                    getDiagnosisByUser(userLastName).forEach(System.out::println);
                }
                case (updateUserLoginCommand) ->
                {
                    String userLogin = arguments[1];
                    String newUserLogin = arguments[2];
                    updateUser(userLogin, newUserLogin);
                }
                case (addPatientCommand) ->
                {
                    String lastName = arguments[1];
                    String firstName = arguments[2];
                    String patronymic = arguments[3];
                    Date birthDate = Date.valueOf(arguments[4]);
                    addPatient(lastName, firstName, patronymic, birthDate);
                }
                case (deletePatientCommand) ->
                {
                    String lastName = arguments[1];
                    String firstName = arguments[2];
                    String patronymic = arguments[3];
                    removePatient(lastName, firstName, patronymic);
                }
                case (exitCommand) -> isContinue = false;
                default -> System.out.println("Unknown command");
            }
        }
    }

    private static void updateUser(String login, String newLogin)
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session
                .createQuery("from User where login = :login", User.class)
                .setParameter("login", login)
                .getResultList()
                .forEach(user ->
                         {
                             System.out.println("User login of " + user + " update to " + newLogin);
                             user.setLogin(newLogin);
                         });

        session.getTransaction().commit();
    }

    private static void removePatient(String lastName, String firstName, String patronymic)
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session
                .createQuery("from Patient where lastName= :lastName and firstName= :firstName and patronymic= :patronymic", Patient.class)
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName)
                .setParameter("patronymic", patronymic)
                .getResultList()
                .forEach(patient ->
                         {
                             System.out.println("Patient " + patient + " removed");
                             session.remove(patient);
                         });

        session.getTransaction().commit();
    }

    private static List<SurveyDiagnosis> getDiagnosisByUser(String lastName)
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<SurveyDiagnosis> surveys = session
                .createQuery("from SurveyDiagnosis where userId.lastName = :lastName", SurveyDiagnosis.class)
                .setParameter("lastName", lastName)
                .getResultList();

        session.getTransaction().commit();

        return surveys;
    }

    private static List<Survey> getSurveysByPatient(String firstName)
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Survey> surveys = session
                .createQuery("from Survey where patientId.firstName = :firstName", Survey.class)
                .setParameter("firstName", firstName)
                .getResultList();

        session.getTransaction().commit();

        return surveys;
    }

    private static void addPatient(String lastName, String firstName, String patronymic, Date date)
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Patient patient = new Patient(lastName, firstName, patronymic, date);

        session.persist(patient);

        session.getTransaction().commit();
    }
}
