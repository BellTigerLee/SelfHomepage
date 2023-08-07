package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TestTable")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

}
