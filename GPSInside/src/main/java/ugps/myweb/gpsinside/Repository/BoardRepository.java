package ugps.myweb.gpsinside.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ugps.myweb.gpsinside.Entity.UserBoard;

public interface BoardRepository extends JpaRepository<UserBoard, Long>, QuerydslPredicateExecutor<UserBoard> {
}
