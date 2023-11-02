package ugps.myweb.gpsinside.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Service.BoardService;

import java.util.Optional;

//게시판을 위한 컨트롤러

@Controller
@RequestMapping(value = {"/b"}, method = RequestMethod.GET)
public class BoardsController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    private final BoardService boardService;


    @GetMapping(value={"", "/main"})
    public String goToBoardPage(Model model,
                                @RequestParam(value = "page", required = false) Optional<Integer> ipage,
                                @RequestParam(value = "size", required = false) Optional<Integer> isize){
        PageRequestDto requestDto = new PageRequestDto(ipage.orElse(1), isize.orElse(5));
        PageResponseDto<UserBoardDto, UserBoard> relation = boardService.getBoardList(requestDto);

        System.out.println(relation.toString());
        model.addAttribute("relation", relation);
        for(UserBoardDto board : relation.getContent())
            log.info(board.toString());
        log.info("Boards 가 요청되었습니다.");
        log.info(relation.toString());

        return "pages/BoardPage";
    }
    /* C R U D 순서 */

    @GetMapping(value = {"/post"})
    public String createBoard(Model model) {
        model.addAttribute("postDto", new UserBoardDto());
        return "pages/PostPage";
    }


    /**
     * 게시판 Read 기능
     * @param model
     * @return read_board
     */
//    @GetMapping(value={"/read"})
//    public String readBoard_View(Model model) {
//
//    }


    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

}
