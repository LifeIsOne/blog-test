package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.AttributeBinderType;

@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AUTO_INCREMENT
    private int id;
    
    @Column(length = 20)
    private String author;

    @Column(length = 20)
    private String title;

    @Column(length = 20)
    private String content;
}
