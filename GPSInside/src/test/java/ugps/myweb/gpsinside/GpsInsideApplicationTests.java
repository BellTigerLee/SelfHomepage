package ugps.myweb.gpsinside;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Repository.BoardRepository;
import ugps.myweb.gpsinside.Repository.UserRepository;
import ugps.myweb.gpsinside.Service.BoardService;
import ugps.myweb.gpsinside.Service.BoardServiceImpl;

import java.util.ArrayList;

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


	@Autowired
	private BoardService boardService;

	@Test
	public void testTest(){
		System.out.println("Hello Test!");
	}

	@Test
	void initiateTest() {
		for(int i=1;i<=100;++i) {
			RegistedUser user = RegistedUser.builder()
					.name("aaa"+i)
					.email("aaa"+i+"@t.com")
					.password("password"+i)
					.boards(new ArrayList<>())
					.build();
			userRepository.save(user);
		}
		for(int i=1;i<=100;++i) {
			UserBoardDto dto = UserBoardDto.builder()
					.writer("aaa"+i)
					.userEmail("aaa"+i+"@t.com")
					.password("password"+i)
					.title("Title"+i)
					.content("Content..."+i)
					.build();

			Long created = boardService.createBoard(dto);
			System.out.println("Created board no is = " + created);
		}
	}

	@Test
	void selectBoardTest1(){
		UserBoardDto dto = boardService.selectBoard(1L );
		System.out.println(dto.toString());
	}

	@Test
	void updateBoardTest1() {
		UserBoardDto dto = boardService.selectBoard(1L);
		System.out.println(dto.toString());
		dto.updateTitle("Update Title...!!!");
		dto.updateContent("Update Content...!!!");
		System.out.println(boardService.updateBoard(dto)+" ) updated!");
	}

	@Test
	void removeBoardTest1(){
		UserBoardDto dto = boardService.selectBoard(1L );
		Long rid = boardService.removeBoard(dto);
		System.out.println("삭제 된 ID : "+rid);
	}

	@Test
	@Transactional
	void getBoardListTest() {
		PageRequestDto requestDto = new PageRequestDto(1, 5);
		PageResponseDto<UserBoardDto, UserBoard> responseDto = boardService.getBoardList(requestDto);
		System.out.println(responseDto.toString());
		for(UserBoardDto dto : responseDto.getContent())
			System.out.println(dto);
		System.out.println("테스트 끝!");
	}

	@Transactional(readOnly = true)
	@Test
	public void searchTest() {
		PageRequestDto pageRequestDto = new PageRequestDto(1, 5);
		//t, c, u
		String tag = "tc";
		String txt = "title";
		PageResponseDto<UserBoardDto, UserBoard> boards = boardService.searchBoardWithCrit(tag, txt, pageRequestDto.getPageable(Sort.by("bno").descending()));
		System.out.println(boards.getContent().size());
		for(UserBoardDto dto : boards.getContent()) {
			System.out.println(dto);
		}
	}

	/*
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
//	@Transactional
	void selectBoardTest(){

		List<UserBoardDto> dtos = boardService.getBoardList();
		for(UserBoardDto dto : dtos){
			System.out.println(dto + dto.getRegDatetime());
		}

	}

	@Test
//	@Transactional
	void createBoardTest() {
//		RegistedUser user = userRepository.findById("aaa10@t.com").get();
		UserBoardDto dto = boardService.entityToDto(boardRepository.findById(104L).get());
		dto.updateTitle("Updated  104Lehoo..");

		Long bno = boardService.createBoard(dto);

		System.out.println("Test bno is " + bno);

	}

	@Test
	@Transactional
	void TestfindUserByBno() {
		Long _bno = 11L;

	}

	*/
}
