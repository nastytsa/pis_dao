package project.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
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
