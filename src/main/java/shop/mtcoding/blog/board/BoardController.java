package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {



    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request,@RequestParam(defaultValue = "0") int page) {
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        //  paging
        int currentPage = page;
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        List<Board> count = boardRepository.countBoard();
        int totalCount = count.size();
        int paging = 5;

        boolean first = currentPage == 0;
        boolean last = currentPage == (totalCount / paging) ;

        request.setAttribute("first", first);
        request.setAttribute("last", last);


        //
        List<Integer> pageList = new ArrayList();
        for (int i = 0; i <= totalCount / paging; i++) {
            pageList.add(i);
        }

        request.setAttribute("pageList", pageList);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO){
        boardRepository.save(requestDTO);

        return "redirect:/";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("board",board);
        return "board/updateForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){


        boardRepository.update(requestDTO, id);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){

        boardRepository.delete(id);

        return "redirect:/";
    }
}
