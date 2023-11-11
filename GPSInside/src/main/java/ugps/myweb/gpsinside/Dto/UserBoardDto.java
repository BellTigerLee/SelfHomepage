package ugps.myweb.gpsinside.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Domain 설명
 * <pre>
 *  boardIdx         not null        Long
 *  writer      not null        varchar
 *  userEmail   not null        varchar
 *  password    not null        varchar
 *
 *  title       not null        varchar(25)
 *  content    texttype
 *
 *  regDatetime                 Date
 *  modDatetime                 Date
 * </pre>
 *
 * 사용자가 직접 입력하는 부분
 * {writer, userEmail, password}, title, content
 * {variable...} 은 OAuth부분 추가하면서 자동적으로 로그인 된 유저의 정보를 읽어오도록 하자.
 */


//@NoArgsConstructor
@Getter
public class UserBoardDto {
    public UserBoardDto(){};
    private Long boardIdx;
    private String writer;
    private String userEmail;
    private String password;
    private String title;
    private String content;

    private String regDateTime;
    private String modDateTime;

    @Builder
    public UserBoardDto(Long boardIdx, String writer, String userEmail, String password, String title, String content, String regDatetime, String modDateTime) {
        this.boardIdx = boardIdx;
        this.writer = writer;
        this.userEmail = userEmail;
        this.password = password;
        this.title = title;
        this.content = content;
        this.regDateTime = regDatetime;
        this.modDateTime = modDateTime;
    }




    public UserBoard toUserBoard(){
        RegistedUser user = RegistedUser.builder()
                .email(userEmail)
                .name(writer)
                .password(password)
                .build();

        UserBoard userBoard =  UserBoard.builder()
                .bno(boardIdx)
                .title(title)
                .content(content)
                .build();
        userBoard.setUser(user);
        return userBoard;
    }

    public void updateTitle(String title) { this.title = title; }
    public void updateContent(String content) { this.content = content; }
    @Override
    public String toString() {
        return "UserBoardDto{" +
                "boardIdx=" + boardIdx +
                ", writer='" + this.writer + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + this.password + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regDatetime='" + regDateTime + '\'' +
                ", modDateTime='" + modDateTime + '\'' +
                '}';
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBoardIdx(Long boardIdx) {
        this.boardIdx = boardIdx;
    }

    public void setRegDateTime(String regDateTime) {
        this.regDateTime = regDateTime;
    }

    public void setModDateTime(String modDateTime) {
        this.modDateTime = modDateTime;
    }
}
