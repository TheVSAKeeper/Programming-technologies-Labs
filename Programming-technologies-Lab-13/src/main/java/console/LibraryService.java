package console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LibraryService
{
    private Subscription subscription;
    private Request request;
    private MailService mailService;

    @Autowired
    public void setSubscription(Subscription subscription)
    {
        this.subscription = subscription;
    }

    @Autowired
    public void setRequest(Request request)
    {
        this.request = request;
    }

    @Autowired
    public void setMailService(MailService mailService)
    {
        this.mailService = mailService;
    }

    public void buy(String... titles)
    {
        request.add(List.of(titles));
        request.getBooks().forEach(book -> subscription.add(book, new Date()));
        subscription.show();
        mailService.sendMail(subscription);
    }

    public void buyByAuthor(String author)
    {
        request.addByAuthor(author);
        request.getBooks().forEach(book -> subscription.add(book, new Date()));
        subscription.show();
        mailService.sendMail(subscription);
    }

    public void returnBook(Date returnDate, String... titles)
    {
        request.add(List.of(titles));

        boolean isAnyReturned = request.getBooks()
                                       .stream()
                                       .filter(book ->
                                               {
                                                   boolean isReturned = subscription.tryReturnBook(book, returnDate);

                                                   if (isReturned)
                                                   {
                                                       System.out.println("Книга " + book + " успешно возвращена");
                                                   }

                                                   return isReturned;
                                               })
                                       .count() != 0;

        if (isAnyReturned)
        {
            mailService.sendMail(subscription);
        }
    }

    public void showSubscription()
    {
        subscription.show();
    }
}