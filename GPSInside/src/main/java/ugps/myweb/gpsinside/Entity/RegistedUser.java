package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = "boards")
//@EqualsAndHashCode(callSuper=false)
public class RegistedUser extends BaseEntity{
    @Id
    @Column(name = "user_email")
    private String email;

    @NotBlank
    @Column(length = 30, nullable = false, name = "user_name")
    private String name;

    @NotBlank
    @Length(min = 5, max=25)
    @Column(nullable = false)
    private String password;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserBoard> boards = new ArrayList<>();

    public RegistedUser(){}

    public void addBoard(UserBoard board){
        if(!boards.contains(board)){
            this.boards.add(board);
            System.out.println("해당 사용자의 Board가 추가되었습니다.");
            return;
        }
        throw new RuntimeException("Duplication board Error ");
    }

}
