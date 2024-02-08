package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(int page){
        final int COUNT = 5;
        int value = page*5;
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC limit ?,?", Board.class);

        query.setParameter(1,value);
        query.setParameter(2,COUNT);

        List<Board> boardList = query.getResultList();

        return boardList;
    }
    public List<Board> countBoard() {
        Query query = em.createNativeQuery("SELECT * FROM board_tb", Board.class);

        List<Board> boardList = query.getResultList();

        return boardList;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO){
        Query query = em.createNativeQuery("INSERT INTO board_tb(author, title, content) VALUES(?,?,?)");
        query.setParameter(1,requestDTO.getAuthor());
        query.setParameter(2,requestDTO.getTitle());
        query.setParameter(3,requestDTO.getContent());

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("DELETE FROM board_tb WHERE id = ?");
        query.setParameter(1,id);

        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.UpdateDTO requestDTO, int id){
        Query query = em.createNativeQuery("UPDATE board_tb SET author = ?, title = ?, content = ? WHERE id = ?");
        query.setParameter(1, requestDTO.getAuthor());
        query.setParameter(2, requestDTO.getTitle());
        query.setParameter(3, requestDTO.getContent());
        query.setParameter(4, id);

        query.executeUpdate();
    }


    public Board findById(int id) {
        Query query = em.createNativeQuery("SELECT * FROM board_tb WHERE id =? ", Board.class);
        query.setParameter(1, id);

        return (Board) query.getSingleResult();
    }

}
