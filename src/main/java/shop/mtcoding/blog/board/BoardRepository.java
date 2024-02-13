package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(){
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC", Board.class);

        return query.getResultList();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("DELETE FROM board_tb WHERE id = ?");
        query.setParameter(1,id);

        query.executeUpdate();
    }
}
