package registration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import registration.Application;
import registration.dao.PersonRepository;
import registration.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by estarcev on 17.08.2018.
 */

@Controller
public class RegistrationController {

    @Autowired
    private PersonRepository repository;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        log.info("open registration form");
        model.addAttribute("person", new Person("test", ""));
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(final HttpServletRequest request, @Valid Person person, BindingResult bindingResult, Model model) {
        if (repository.findByEmail(person.getEmail()) != null) {
            bindingResult.addError(new FieldError("person", "email", "This EMail is already registered."));
            log.error("This EMail is already registered: "+person.getEmail());
        }
        if (!person.isPassMismatch()) {
            bindingResult.addError(new FieldError("person", "confPass", "Password and confirmation mismatch."));
            log.error("Password and confirmation mismatch.");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        log.info("New person created: "+person.getEmail());

        repository.save(person);
        try {
            request.login(person.getEmail(), person.getPassword());
        } catch (ServletException e) {
            log.error("error auto login..."+e.getMessage());
        }

        model.addAttribute("person", person);


        return "redirect:/hello";
    }
}
