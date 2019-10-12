package ec.com.nashira.callcenter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NashiraCallcenterApplication implements CommandLineRunner {


  public static void main(String[] args) {
    SpringApplication.run(NashiraCallcenterApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // Method to generated encoded passwords if necessary
  }

}
