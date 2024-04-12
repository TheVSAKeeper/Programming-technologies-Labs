package console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class BookService
{
    private List<Book> books;

    @Autowired
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

    @PostConstruct
    private void addTopRatedBooks()
    {
        books.add(new Book("Мастер и Маргарита", "Михаил Булгаков"));
        books.add(new Book("Ася", "Иван Тургенев"));
        books.add(new Book("Мертвые души", "Николай Гоголь"));
        books.add(new Book("Хождение по мукам", "Алексей Толстой"));
        books.add(new Book("Записки охотника", "Иван Тургенев"));
        books.add(new Book("Война и мир", "Лев Толстой"));
        books.add(new Book("Левша", "Николай Лесков"));
        books.add(new Book("Двенадцать стульев", "Илья Ильф"));
        books.add(new Book("Обыкновенная история", "Иван Гончаров"));
        books.add(new Book("Герой нашего времени", "Михаил Лермонтов"));
    }

    public void printAll()
    {
        System.out.println("Список книг");
        books.forEach(System.out::println);
    }

    public Book findByTitle(String title)
    {
        return books.stream()
                    .filter(book -> book.getTitle().compareToIgnoreCase(title) == 0)
                    .findFirst()
                    .orElse(null);
    }

    public List<Book> findByAuthor(String author)
    {
        return books.stream()
                    .filter(book -> book.getAuthor().compareToIgnoreCase(author) == 0)
                    .toList();
    }
}