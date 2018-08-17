package registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import registration.dao.PersonRepository;
import registration.model.Person;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Create test user");
        Person person = new Person("u@u.u", "pass");
        repository.save(person);
    }

}