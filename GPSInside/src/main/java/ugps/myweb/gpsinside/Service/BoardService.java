package ugps.myweb.gpsinside.Service;

import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BoardService {
    List<UserBoardDto> getBoardList();

    Long createBoard(UserBoardDto dto);

    @Transactional
    UserBoardDto selectBoard(Long bno, int page_num);

    Long updateBoard(UserBoardDto updated);

    Long removeBoard(UserBoardDto removed);

    default UserBoardDto entityToDto(UserBoard board) {
        String date_form = "yyyy-MM-dd HH:mm:ss";
        return UserBoardDto.builder()
                .boardIdx(board.getBno())
                .title(board.getTitle())
//                .writer(board.getWriter())
                .userEmail(board.getUser().getEmail())
                .regDatetime(board.getRegDate().format(DateTimeFormatter.ofPattern(date_form)))
                .modDateTime(board.getModDate().format(DateTimeFormatter.ofPattern(date_form)))
                .build();
    }

    default UserBoardDto daoToDto(UserBoard board, RegistedUser user){
        String date_form = "yyyy-MM-dd HH:mm:ss";
        return UserBoardDto.builder()
                .boardIdx(board.getBno())
                .writer(user.getName())
                .userEmail(user.getEmail())
                .password(user.getPassword())
                .title(board.getTitle())
                .content(board.getContent())
                .regDatetime(board.getFormmedRegDate())
                .modDateTime(board.getFormmedModDate())
                .build();

    }


}
