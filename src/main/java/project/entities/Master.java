package project.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Locale;

@Entity
@NoArgsConstructor
@ToString
public class Master{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    @Getter
    @Setter
    protected Long id;
    @Getter
    @Setter
    protected String name;
    @Getter
    @Setter
    protected String password;
    @Enumerated(value = EnumType.STRING)
    private Rate rate;
    
    public String getRate(){
        return this.rate.name();
    }
    
    public void setRate(String rate){
        if (rate == null || rate == "")
            this.rate = Rate.NOVICE;
        else
            this.rate = Rate.valueOf(rate.toUpperCase(Locale.ROOT));
    }
    
    public Master(Long id, String name, String password, String rate){
        setId(id);
        setName(name);
        setPassword(password);
        setRate(rate);
    }
    
    public Master(String name, String password, String rate){
        setId(id);
        setName(name);
        setPassword(password);
        setRate(rate);
    }
}
