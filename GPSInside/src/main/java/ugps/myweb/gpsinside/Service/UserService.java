package ugps.myweb.gpsinside.Service;

import ugps.myweb.gpsinside.Entity.RegistedUser;

public interface UserService {
    RegistedUser findUserByBno(Long bno);
}
