package in.nks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RegistrationMicroservice
{
	public static void main(String[] args)
	{
		SpringApplication.run(RegistrationMicroservice.class, args);
	}
}
