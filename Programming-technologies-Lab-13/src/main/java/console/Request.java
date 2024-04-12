package console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class Request
{
    private BookService bookService;
    private List<Book> books;

    @Autowired
    public void setBookService(BookService bookService)
    {
        this.bookService = bookService;
    }

    public List<Book> getBooks()
    {
        ArrayList<Book> request = new ArrayList<>(books);
        books.clear();
        return request;
    }

    @Autowired
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    public void add(List<String> titles)
    {
        List<Book> buyingBooks = titles.stream()
                                       .map(title -> bookService.findByTitle(title))
                                       .filter(Objects::nonNull)
                                       .toList();

        books.addAll(buyingBooks);
    }

    public void addByAuthor(String author)
    {
        books.addAll(bookService.findByAuthor(author));
    }
}