package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

//tis is huge. What makes a class a Spring Component is the @Component tag
// plus it must implement the CommandLineRunner Interface
@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner{

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll(); //added Lesson 33

        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");
        //System.out.println("Id: " + bookDDD.getId());
        Book savedDDD = bookRepository.save(bookDDD);

        //System.out.println("Id: " + savedDDD.getId());
        Book bookSIA = new Book("Spring in Action", "234234", "OReilly");
        Book savedSIA = bookRepository.save(bookSIA);

        Book bookMIA = new Book("Struts in Action", "567567", "O'Reilly");
        Book savedMIA = bookRepository.save(bookMIA);

        bookRepository.findAll().forEach(book -> {
             System.out.println("Id: " + book.getId());
             System.out.println("Title : " + book.getTitle());
        });
    }
}
