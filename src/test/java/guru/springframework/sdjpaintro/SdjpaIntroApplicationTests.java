package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repositories.BookRepository;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*; //static import assertJ very nice assertion library
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Note: this class is created for us by the Spring Boot initializer
//      out of the box.
@SpringBootTest
class SdjpaIntroApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookRepository(){
        long count = bookRepository.count();
        assertThat(count).isGreaterThan(0); //
    }

    @Test
    void contextLoads() {
    }

}
