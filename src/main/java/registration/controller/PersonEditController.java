package registration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import registration.Application;
import registration.dao.PersonRepository;
import registration.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by estarcev on 17.08.2018.
 */
@Controller
public class PersonEditController {

    @Autowired
    private PersonRepository repository;

    private static final Logger log = LoggerFactory.getLogger(PersonEditController.class);

    @GetMapping("/hello")
    public String helloForm(final HttpServletRequest request, final Model model) {

        try {
            Person person = repository.findByEmail(request.getRemoteUser());
            model.addAttribute("person", person);
            log.info("edit existing "+person.getEmail());
            return "hello";
        } catch (Exception e) {
            log.error("error editing user "+ e.getMessage());
            return "login";
        }
    }

    @PostMapping("/hello")
    public String registrationSubmit(final HttpServletRequest request, @Valid Person person, BindingResult bindingResult, Model model) {

        try {
            Person experson = repository.findByEmail(request.getRemoteUser());
            experson.setNickname(person.getNickname());
            repository.save(experson);

            model.addAttribute("person", experson);
            log.info("update user "+experson.getEmail());
        } catch (Exception e) {
            log.error("error update user "+ e.getMessage());
            return "login";
        }

        return "/hello";
    }
}
