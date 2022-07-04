package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// from Lesson 33 20220704
@ActiveProfiles("local") //Use the db configured in the local config properties file
@DataJpaTest //HOwever here SpringBoot is overriding the local config
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"}) //adding the ComponentScan will add all the files in the package, esp DataInitializer
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Says do not replace what we have configured
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testMySQL(){
        long countBefore = bookRepository.count();
        //In the Spring default state this will fail because now that we're using the @DataJpaTest slice we don't get the initializer context
        //however because we committed the data from the first test. Usally each test should clean up its own data
        assertThat(countBefore).isEqualTo(3);
    }


}
