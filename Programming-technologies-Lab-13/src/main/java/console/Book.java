package console;

import java.text.MessageFormat;

public class Book
{
    private static int globalId = 0;
    private final int id;
    private final String title;
    private final String author;

    public Book(int id, String title, String author)
    {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author)
    {
        this(globalId++, title, author);
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    @Override
    public String toString()
    {
        return MessageFormat.format("({0}) {1}, автор {2}", id, title, author);
    }
}