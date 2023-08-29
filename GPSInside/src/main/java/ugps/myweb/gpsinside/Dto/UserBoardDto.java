package ugps.myweb.gpsinside.Dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserBoardDto {
    private Long boardIdx;
    private String title;
    private String username;
    private String regDatetime;
    private String modDateTime;

    @Builder
    public UserBoardDto(Long boardIdx, String title, String username, String regDatetime, String modDateTime) {
        this.boardIdx = boardIdx;
        this.title = title;
        this.username = username;
        this.regDatetime = regDatetime;
        this.modDateTime = modDateTime;
    }
}
