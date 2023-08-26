package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name="board_table")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@Getter
@Setter
public class UserBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @NotBlank
    @Column(length = 25, nullable = false)
    private String title;

    @NotBlank
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_info")
    private RegistedUser user;

}
