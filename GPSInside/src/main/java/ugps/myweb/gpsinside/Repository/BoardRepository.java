package ugps.myweb.gpsinside.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ugps.myweb.gpsinside.Entity.UserBoard;

@Repository
public interface BoardRepository extends JpaRepository<UserBoard, Long> {

}
