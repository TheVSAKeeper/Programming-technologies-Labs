package console;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class MailService
{
    public void sendMail(Subscription subscription)
    {
        System.out.println("Отправлено письмо на почту, книги в абонементе:");

        for (int i = 0; i < subscription.getBooks().size(); i++)
        {
            if (subscription.getReturnDates().get(i) == null)
            {
                System.out.println(MessageFormat.format("{0}, дата выдачи: {1}",
                                                        subscription.getBooks().get(i),
                                                        subscription.getIssueDates().get(i)));
            }
        }
    }
}