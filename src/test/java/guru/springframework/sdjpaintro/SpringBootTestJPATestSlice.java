package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

//SpringBootTest is good but it brings up hte entire context
//DataJpaTest will only load the JPA context, so its much lighter to load
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //TestMethodOrder new for JUnit 5
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"}) //adding the ComponentScan will add all the files in the package
public class SpringBootTestJPATestSlice {

    @Autowired
    BookRepository bookRepository;

    //the default behavior is to roll back test data after the test has completed
    //however in this edge case, we want the test data to persist into the next test
    @Commit //the Commit annottion inlcudes @Rollback(value = false)
    @Order(1)
    @Test
    void testJpaTestSplice(){
        long countBefore = bookRepository.count();
        //this will fail because now that we're using the @DataJpaTest slice we don't get the DataInitializer context
        assertThat(countBefore).isEqualTo(3);

        bookRepository.save(new Book("My Book", "1234567", "Self"));
        long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction(){
        long countBefore = bookRepository.count();
        //In the Spring default state this will fail because now that we're using the @DataJpaTest slice we don't get the initializer context
        //however because we committed the data from the first test. Usally each test should clean up its own data
        assertThat(countBefore).isEqualTo(4);
    }
}
