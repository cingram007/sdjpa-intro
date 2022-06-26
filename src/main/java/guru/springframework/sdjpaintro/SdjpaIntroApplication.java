package guru.springframework.sdjpaintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SdjpaIntroApplication {

    //Note: this run method will run the run method found on the class path
    public static void main(String[] args) {
        SpringApplication.run(SdjpaIntroApplication.class, args);
    }

}
