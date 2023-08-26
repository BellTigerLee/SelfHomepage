package ugps.myweb.gpsinside.Entity;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Repository.BoardRepository;
import ugps.myweb.gpsinside.Repository.UserRepository;

@SpringBootTest
@Slf4j
class TestEntityTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    @Transactional
    void initialized(){
        for(int i=1;i<=100;++i){
            UserBoard board = UserBoard.builder()
                    .title("test title"+i)
                    .writer("aaa"+i+"@t.com")
                    .build();
            System.out.println("Board 생성!!");
            boardRepository.save(board);
            System.out.println("Board 저장!");
            RegistedUser user = RegistedUser.builder()
                    .email("aaa"+i+"@t.com")
                    .name("aaa"+i)
                    .build();
            user.addBoard(board);
            System.out.println("Board 를 User에 저장!");
            userRepository.save(user);
            System.out.println("User를 저장!");
        }





    }

}