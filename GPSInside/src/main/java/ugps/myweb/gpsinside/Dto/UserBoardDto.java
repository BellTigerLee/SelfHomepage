package ugps.myweb.gpsinside.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
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
 */


@Getter
public class UserBoardDto {
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
}
