package ugps.myweb.gpsinside.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;
    @LastModifiedDate
    private LocalDateTime modDate;

    @JsonIgnore
    public String getFormmedRegDate(){
        return transDateByString(regDate);
    }
    @JsonIgnore
    public String getFormmedModDate() {
        return transDateByString(modDate);
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }


    private String transDateByString(LocalDateTime _date) {
        if(_date == null) return "";
        return _date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

//    @PrePersist
//    public void prePersist(){
//        this.regDate = LocalDateTime.now();
//        this.modDate = this.regDate;
//    }
//    @PreUpdate
//    public void preUpdate(){
//        this.modDate = LocalDateTime.now();
//    }
}
