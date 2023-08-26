package ugps.myweb.gpsinside.Entity;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @PrePersist
    public void prePersist(){
        this.regDate = LocalDateTime.now();
        this.modDate = this.regDate;
    }

    @PreUpdate
    public void preUpdate(){
        this.modDate = LocalDateTime.now();
    }
}
