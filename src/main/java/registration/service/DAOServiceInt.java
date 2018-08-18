package registration.service;

import registration.model.Person;

/**
 * Created by jago2 on 18.08.2018.
 */
public interface DAOServiceInt {

    Person findByEmail(String eMail);

    Person save(Person person);
}
