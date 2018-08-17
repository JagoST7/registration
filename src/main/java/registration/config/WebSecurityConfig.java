package registration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import registration.dao.PersonRepository;
import registration.model.Person;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PersonRepository repository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/registration", "/resources/**").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/hello").permitAll()
                .and().logout().permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            Person person = repository.findByEmail(username);
            if (person == null) {
                throw new UsernameNotFoundException(username);
            }
            return WebSecurityConfig.this.convertUser(person);
        };
    }


    private UserDetails convertUser(Person user) {
        return User.withDefaultPasswordEncoder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}