package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = "boards")
@EqualsAndHashCode(callSuper=false)
public class RegistedUser extends BaseEntity{
    @Id
    @Column(name = "user_email")
    private String email;

    @NotBlank
    @Column(length = 30, nullable = false, name = "user_name")
    private String name;


    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBoard> boards = new ArrayList<>();

    public void addBoard(UserBoard board){
        this.boards.add(board);
        board.setUser(this);
    }

}
