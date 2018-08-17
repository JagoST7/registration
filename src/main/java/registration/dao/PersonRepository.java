package registration.dao;

import org.springframework.data.repository.CrudRepository;
import registration.model.Person;

/**
 * Created by estarcev on 17.08.2018.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByEmail(String eMail);
}
