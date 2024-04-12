package console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@ComponentScan("console")
public class SpringConfig
{
    @Bean
    @Scope("prototype")
    public List<Book> booksList()
    {
        return new ArrayList<>();
    }

    @Bean
    @Scope("prototype")
    public List<Date> datesList()
    {
        return new ArrayList<>();
    }
}