package project.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long feedback_id;
    @Getter
    @Setter
    @Column(name = "feedback")
    private String feedback_content;
    
    @Getter
    @Setter
    private Long order_id;
    
}
