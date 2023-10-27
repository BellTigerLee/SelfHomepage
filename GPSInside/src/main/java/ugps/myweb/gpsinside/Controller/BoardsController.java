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


    @GetMapping(value = {"", "/main", "/main/{page}"})
    public String goToDefaultBoardPage(@PathVariable(value="page", required = false) Optional<Integer> page) {
        return "redirect:/b/main/"+page.orElse(1)+"/10";
    }

    @GetMapping(value={"/main/{page}/{size}"})
    public String goToBoardPage(Model model,
                                @PathVariable(value = "page", required = false) Optional<Integer> ipage,
                                @PathVariable(value = "size", required = false) Optional<Integer> isize){
        PageRequestDto requestDto = new PageRequestDto(ipage.orElse(1), isize.orElse(10));
        PageResponseDto<UserBoardDto, UserBoard> relation = boardService.getBoardList(requestDto);

        model.addAttribute("relation", relation);
        for(UserBoardDto board : relation.getContent())
            log.info(board.toString());
        log.info("Boards 가 요청되었습니다.");
        log.info(relation.toString());

        return "pages/BoardPage";
    }




    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

}
