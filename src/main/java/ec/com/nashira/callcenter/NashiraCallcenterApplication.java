package ec.com.nashira.callcenter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NashiraCallcenterApplication implements CommandLineRunner {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(NashiraCallcenterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String password = "nashira";
//		for (int i = 0; i < 4; i++) {
//			System.out.println(passwordEncoder.encode(password));
//		}

	}

}
