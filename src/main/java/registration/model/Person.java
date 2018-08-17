package registration.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by estarcev on 17.08.2018.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String confPass;

    private String nickname;

    public Person() {
    }

    public Person(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Person[id=%d, eMail='%s']", id, email);
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String password) {
        this.confPass = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isPassMismatch(){
        return password != null && password.equals(confPass);
    }
}
