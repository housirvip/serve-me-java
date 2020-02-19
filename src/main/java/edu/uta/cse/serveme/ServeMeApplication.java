package edu.uta.cse.serveme;

import edu.uta.cse.serveme.repository.SimpleJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author spring boot generated automaticlly
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepositoryImpl.class)
public class ServeMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeMeApplication.class, args);
    }
}
