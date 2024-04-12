package console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Subscription
{
    private List<Book> books;
    private List<Date> issueDates;
    private List<Date> returnDates;

    public List<Book> getBooks()
    {
        return books;
    }

    @Autowired
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public List<Date> getIssueDates()
    {
        return issueDates;
    }

    @Autowired
    public void setIssueDates(List<Date> issueDates)
    {
        this.issueDates = issueDates;
    }

    public List<Date> getReturnDates()
    {
        return returnDates;
    }

    @Autowired
    public void setReturnDates(List<Date> returnDates)
    {
        this.returnDates = returnDates;
    }

    public void add(Book book, Date issueDate)
    {
        books.add(book);
        issueDates.add(issueDate);
        returnDates.add(null);

        System.out.println("Книга добавлена " + book + " в абонемент. Дата " + issueDate);
    }

    public boolean tryReturnBook(Book book, Date returnDate)
    {
        int index = books.indexOf(book);

        if (index == -1)
        {
            return false;
        }

        returnDates.set(index, returnDate);

        books.remove(index);
        issueDates.remove(index);
        returnDates.remove(index);
        return true;
    }

    public void show()
    {
        System.out.println("Абонемент:");

        for (int i = 0; i < books.size(); i++)
        {
            System.out.println(books.get(i) + ", дата выдачи: " + issueDates.get(i) + ", дата возврата: " + returnDates.get(i));
        }

        System.out.println("Итоговое количество книг: " + books.size());
    }
}