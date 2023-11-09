package ugps.myweb.gpsinside.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Repository.BoardRepository;
import ugps.myweb.gpsinside.Repository.UserRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

//    @Override
//    public List<UserBoardDto> getBoardList() {
//        Pageable sortedByBno = PageRequest.of(0, 10, Sort.by("bno").descending());
//        Page<UserBoard> page = boardRepository.findAll(sortedByBno);
//        List<UserBoardDto> dtoPage = page.get().map(function).toList();
//
//        return dtoPage;
//    }

    @Override
    public PageResponseDto<UserBoardDto, UserBoard> getBoardList(PageRequestDto request) {
        Page<UserBoard> daoList = boardRepository.findAll(request.getPageable(Sort.by("bno").descending()));
        return new PageResponseDto<>(daoList, function);
    }

    @Override
    public Long createBoard(UserBoardDto dto) {
        UserBoard board = dto.toUserBoard();
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public UserBoardDto selectBoard(Long bno) {
        UserBoard board = boardRepository.findById(bno).get();
        UserBoardDto ret_dto = null;
        if(board.getUser() == null){
            return null;
        }
        RegistedUser user = board.getUser();
        ret_dto = entityToDto(board);
        // 올바른 값이 맞는지 검사하는 코드 추가하기
        return ret_dto;
    }

    @Override
    public Long updateBoard(UserBoardDto updated) {
        System.out.println("Update 하는 시퀀스가 실행되었습니다.");
        System.out.println("이제부터 업데이트를 진행합니다......");
        System.out.println("...");
        return createBoard(updated);
    }

    @Override
    public Long removeBoard(UserBoardDto removed) {
        System.out.println("Remove 하는 시퀀스 실행......");
        System.out.println("이제부터 삭제작업을 진행합니다......");
        UserBoard board = removed.toUserBoard();
        if(board == null) throw new RuntimeException("Dto -> Entity 작업 과정에 문제가 생겼습니다.");
        boardRepository.deleteById(board.getBno());
        return board.getBno();
    }


    private Function<UserBoard, UserBoardDto> function = (entity) -> entityToDto(entity);


    //생성자 기반 DI
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

}
