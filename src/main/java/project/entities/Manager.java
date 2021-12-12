package project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected String password;
    
    public Manager(String name, String password) {
        
        this.name = name;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Manager["
                + "id=" + id
                + ", name=" + name
                + ", password=" + password
                + ']';
    }
}
