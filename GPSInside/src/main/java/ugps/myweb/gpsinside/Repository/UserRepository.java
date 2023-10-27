package ugps.myweb.gpsinside.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ugps.myweb.gpsinside.Entity.RegistedUser;

public interface UserRepository extends JpaRepository<RegistedUser, String> {
}
