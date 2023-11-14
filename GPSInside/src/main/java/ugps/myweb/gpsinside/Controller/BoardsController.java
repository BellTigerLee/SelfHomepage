package ugps.myweb.gpsinside.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Service.BoardService;

import java.util.Optional;

//게시판을 위한 컨트롤러

@Controller
//@RequestMapping(value = {"/b"}, method = RequestMethod.GET)
public class BoardsController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    private final BoardService boardService;


    @GetMapping(value={"/b", "/b/main"})
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

    @GetMapping(value = {"/b/post"})
    public String createBoard(Model model) {
        model.addAttribute("postDto", new UserBoardDto());
        return "pages/PostPage";
    }



    @RequestMapping(value = {"/b/post"}, method = RequestMethod.POST)
    public String post_createBoard(UserBoardDto postDto, RedirectAttributes attributes) {
        System.out.println("*****************\n"+postDto);
        Long nb = boardService.createBoard(postDto);
        System.out.println("NUMBER IS "+nb);
        attributes.addFlashAttribute("msg", nb);
        return "redirect:/b/main";
    }

    /**
     * 게시판 Read 기능
     * @param model
     * @return read_board
     */
    @GetMapping(value={"/b/read"})
    public String readBoard_View(@RequestParam("bno") Long req_bno,
                                 @ModelAttribute("requestDto") PageRequestDto requestDto,
                                 Model model){
        log.info("보드 "+req_bno+" 번을 조회합니다.");
        UserBoardDto dto = boardService.selectBoard(req_bno);
        model.addAttribute("dto", dto);
        return "pages/ReadBoardPage";
    }

    @GetMapping(value = {"/b/update"})
    public String updateBoard(@RequestParam("bno") Long bno,
                              @ModelAttribute("requestDto") PageRequestDto requestDto,
                              Model model) {

        model.addAttribute("dto", boardService.selectBoard(bno));
        return "pages/UpdateBoardPage";
    }

    @PostMapping(value = {"/b/update"})
    public String updateBoard_post(UserBoardDto dto,
                                 RedirectAttributes attr) {
        log.info("***Board Update요청이 왔습니다.");
        Long ubno = boardService.updateBoard(dto);
        System.out.println(dto);
        attr.addFlashAttribute("msg", ubno);
        return "redirect:/b/main";
    }


    /**
     * 비효율 적인 코드.
     * remove를 위해 select 한번 조회 후 삭제하는 방식임.
     * 이거 반드시 고쳐야 할 필요가 있음.
     * @param bno
     * @param attr
     * @return
     */
    @PostMapping(value = {"/b/delbrd"})
    public String deleteBoardById(@RequestParam("bno") Long bno,
                                  RedirectAttributes attr) {
        log.info("삭제 실행 : "+bno);
        Long removed = boardService.removeBoard(boardService.selectBoard(bno));
        attr.addFlashAttribute("msg", removed);
        log.info(removed+"번 게시물이 삭제되었습니다.");
        return "redirect:/b/main";
    }

    public BoardsController(BoardService boardService) {
        this.boardService = boardService;
    }

}


/**
 *==============================
 *
 * https://github.com/spring-projects/spring-framework/issues/18161
 * 왜 루트 url를 삭제하였는지 이유.
 * issue: ambiguous handler methods mapped for HTTP path
 *
 *==============================
 *
 * object references an unsaved transient instance
 * FK연관관계로 저장해야 할 시 참조하고 있는 키 값을 모르는데 저장하려했을 때
 * 생기는 오류. 나같은 경우 UserBoard의 Cascade.Persist를 빼주었기 때문에
 * 저장이 ServiceImpl단에서 save 시 FK의 user_email의 값이 없어서 발생했음.
 *
 * Persist로 바꿔주니 해결됨(저장만 하려고 PERSIST사용함)
 *
 * 2차문제. 없는 ID를 넣고 게시물 저장 시 오류생김. UserBoard의 ALL을 없애버렸음.
 * ERROR: 사용자 탈퇴(삭제) 시 모든 게시물 삭제만 고려하기 때문에 UsreBoard의(부모) Cascade는 없애버림.
 * ==============================
 *
 */