package ugps.myweb.gpsinside;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Repository.BoardRepository;
import ugps.myweb.gpsinside.Repository.UserRepository;
import ugps.myweb.gpsinside.Service.BoardService;
import ugps.myweb.gpsinside.Service.BoardServiceImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
//@DataJpaTest
@ComponentScan(basePackages = {"ugps.myweb"})
class GpsInsideApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	BoardRepository boardRepository;
	@Autowired
	UserRepository userRepository;

	@Test
	void insertion() {

		for (int i = 1; i <= 100; ++i) {
			UserBoard board = UserBoard.builder()
					.title("test title" + i)
					.writer("aaa" + i)
					.build();
			boardRepository.save(board);

		}
		System.out.println("User 입력 실행!!!!");

		for(int i=1;i<=100;++i){
			RegistedUser user = RegistedUser.builder()
			   .email("aaa"+i+"@t.com")
			   .name("aaa"+i)
//					.boards(new ArrayList<>())
			   .build();
			UserBoard board = boardRepository.findById(Long.valueOf(i)).get();
			System.out.println(i+") INFO : "+board);
			user.addBoard(board);
			userRepository.save(user);

		}

	}

	@Test
	@Transactional
	void findTest(){
		RegistedUser user = userRepository.findById("aaa22@t.com").get();
		System.out.println("USER정보 : "+user);
		if(user.getBoards().isEmpty() || user.getBoards().size()==0)
			System.out.println("Userboard 가 0개입니다.");
		for(UserBoard board : user.getBoards())
			System.out.println("Board 정보 : " + board);

	}


	@Autowired
	private BoardService boardService;
	@Test
	@Transactional
	void selectBoardTest(){

		List<UserBoardDto> dtos = boardService.getBoardList();

		for(UserBoardDto dto : dtos){
			System.out.println(dto);
		}
	}
}
