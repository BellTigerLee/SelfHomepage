package ugps.myweb.gpsinside.Service;

import org.springframework.stereotype.Service;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BoardService {
    List<UserBoardDto> getBoardList();

    default UserBoardDto entityToDto(UserBoard board){
        String date_form = "yyyy-MM-dd HH:mm:ss";
        return UserBoardDto.builder()
                .boardIdx(board.getBno())
                .title(board.getTitle())
                .username(board.getWriter())
                .regDatetime(board.getRegDate().format(DateTimeFormatter.ofPattern(date_form)))
                .modDateTime(board.getModDate().format(DateTimeFormatter.ofPattern(date_form)))
                .build();

    }
}
