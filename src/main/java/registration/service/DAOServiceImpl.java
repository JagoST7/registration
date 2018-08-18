package registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registration.dao.PersonRepository;
import registration.model.Person;

/**
 * Created by jago2 on 18.08.2018.
 */

@Service
public class DAOServiceImpl implements DAOServiceInt {

    @Autowired
    private PersonRepository repository;

    @Override
    public Person findByEmail(String eMail) {
        return repository.findByEmail(eMail);
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }
}
