package console;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        LibraryService library = context.getBean(LibraryService.class);

        //library.buy("Ася", "Левша");
        library.buyByAuthor("Иван Тургенев");

        library.returnBook(new Date(), "Ася", "Записки охотника");

        //library.showSubscription();
    }
}