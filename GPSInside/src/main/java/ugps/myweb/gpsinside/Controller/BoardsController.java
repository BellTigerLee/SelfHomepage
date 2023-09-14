package ugps.myweb.gpsinside.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Service.BoardService;

import java.util.List;

//게시판을 위한 컨트롤러

@RestController
public class BoardsController {

    private final Logger log = LoggerFactory.getLogger(getClass());



    private final BoardService boardService;



    @ResponseBody
    @GetMapping(value={"/boards/{page}"})
    public PageResponseDto goToBoardPage(Model model, @PathVariable(value = "page", required = false) Integer page){
        PageRequestDto requestDto = new PageRequestDto(page);
        PageResponseDto<UserBoardDto, UserBoard> relation = boardService.getBoardList(requestDto);
        model.addAttribute("relation", relation);
        for(UserBoardDto board : relation.getContent())
            log.info(board.toString());
        log.info("Boards 가 요청되었습니다.");
        log.info(relation.toString());

        return relation;
    }




    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

}
