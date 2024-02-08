package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.AttributeBinderType;

@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id

    private int id;

}
