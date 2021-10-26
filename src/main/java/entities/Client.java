package entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.security.auth.login.AppConfigurationEntry;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    @Getter
    protected Long id;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected String password;
    @Getter
    @Setter
    private String email;

    public Client(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
