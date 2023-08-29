package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name="board_table")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"user"})
@Getter
public class UserBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @NotBlank
    @Column(length = 25, nullable = false)
    private String title;

    @NotBlank
    private String writer;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_info")
    private RegistedUser user;



}
