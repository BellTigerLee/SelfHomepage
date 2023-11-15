package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <pre>
 * Domain 설명
 *  bno         not null        Long
 *  title       not null        varchar(25)
 *  content                     varchar(2000)
 *  user        not null        REGISTED USER
 *  regDate                     Date
 *  modDate                     Date
 *  </pre>
 */

@Entity
@Table(name="board_table")
@SuperBuilder
@NoArgsConstructor
@ToString(exclude = {"user"})
@Getter
public class UserBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @NotBlank
    @Column(length = 25, nullable = false)
    private String title;

    @Column(length=2000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_info")
    private RegistedUser user;

    public void setUser(RegistedUser _user){
        if(this.user == null) this.user = _user;
        _user.addBoard(this);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }




}
