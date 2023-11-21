package ugps.myweb.gpsinside.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BoardService {

    PageResponseDto<UserBoardDto, UserBoard>  getBoardList(PageRequestDto request);
    Long createBoard(UserBoardDto dto);

    @Transactional
    UserBoardDto selectBoard(Long bno);

    Long updateBoard(UserBoardDto updated);

    Long removeBoard(UserBoardDto removed);
    
//    private PageResponseDto<UserBoardDto, UserBoard> searchBoardWithCrit(String tag, String txt, Pageable pageable);

    default UserBoardDto entityToDto(UserBoard board) {
        String date_form = "yyyy-MM-dd HH:mm:ss";
        return UserBoardDto.builder()
                .boardIdx(board.getBno())
                .title(board.getTitle())
                .writer(board.getUser().getName())
                .password(board.getUser().getPassword())
                .userEmail(board.getUser().getEmail())
                .content(board.getContent())
                .regDatetime(board.getRegDate().format(DateTimeFormatter.ofPattern(date_form)))
                .modDateTime(board.getModDate().format(DateTimeFormatter.ofPattern(date_form)))
                .build();
    }

//    default UserBoardDto daoToDto(UserBoard board, RegistedUser user){
//        String date_form = "yyyy-MM-dd HH:mm:ss";
//        return UserBoardDto.builder()
//                .boardIdx(board.getBno())
//                .writer(user.getName())
//                .userEmail(user.getEmail())
//                .password(user.getPassword())
//                .title(board.getTitle())
//                .content(board.getContent())
//                .regDatetime(board.getFormmedRegDate())
//                .modDateTime(board.getFormmedModDate())
//                .build();
//
//    }


}
