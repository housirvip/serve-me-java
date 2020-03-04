package edu.uta.cse.serveme;

import edu.uta.cse.serveme.repository.MyJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author spring boot generated automaticlly
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories(repositoryBaseClass = MyJpaRepository.class)
public class ServeMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServeMeApplication.class, args);
    }
}
