package ugps.myweb.gpsinside.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransientPropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ugps.myweb.gpsinside.Dto.PageRequestDto;
import ugps.myweb.gpsinside.Dto.PageResponseDto;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.QUserBoard;
import ugps.myweb.gpsinside.Entity.RegistedUser;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Repository.BoardRepository;
import ugps.myweb.gpsinside.Repository.UserRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(getClass());

//    @Override
//    public List<UserBoardDto> getBoardList() {
//        Pageable sortedByBno = PageRequest.of(0, 10, Sort.by("bno").descending());
//        Page<UserBoard> page = boardRepository.findAll(sortedByBno);
//        List<UserBoardDto> dtoPage = page.get().map(function).toList();
//
//        return dtoPage;
//    }

    @Transactional(readOnly = true)
    @Override
    public PageResponseDto<UserBoardDto, UserBoard> getBoardList(PageRequestDto request) {
        Pageable pageable = request.getPageable(Sort.by("bno").descending());
        BooleanBuilder booleanBuilder = getSearchBuilder(request);

        Page<UserBoard> daoList = boardRepository.findAll(booleanBuilder, pageable);

        return new PageResponseDto<>(daoList, function);
    }

    @Override
    public Long createBoard(UserBoardDto dto) {
        UserBoard board = dto.toUserBoard();
        try{
            Long bno = boardRepository.save(board).getBno();
            return bno;
        }catch(TransientPropertyValueException nonUserInfo_coming){
            log.info("존재하지 않는 사용자의 이름으로 글이 등록되었습니다.");
            return null;
        }
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
        System.out.println("Update가 하는 시퀀스를 진행함.");
        System.out.println(updated.getBoardIdx()+ "번 이제부터 업데이트를 진행함.");
        Optional<UserBoard> result = boardRepository.findById(updated.getBoardIdx());
        if(result.isPresent()) {
            UserBoard board = result.get();
            board.updateTitle(updated.getTitle());
            board.updateContent(updated.getContent());
            return boardRepository.save(board).getBno();
        }
        return -1L;
    }

    @Transactional
    @Override
    public Long removeBoard(UserBoardDto removed) {
        System.out.println("Remove 하는 시퀀스 실행......");
        System.out.println("이제부터 삭제작업을 진행합니다......");
        UserBoard board = removed.toUserBoard();
        if(board == null) throw new RuntimeException("Dto -> Entity 작업 과정에 문제가 생겼습니다.");
        boardRepository.deleteById(board.getBno());
        return board.getBno();
    }




    private BooleanBuilder getSearchBuilder(PageRequestDto requestDto) {
        String rType = requestDto.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QUserBoard qUserBoard = QUserBoard.userBoard;
        String rKey = requestDto.getKeyword();

        BooleanExpression expression = qUserBoard.bno.gt(0L);
        booleanBuilder.and(expression);

        if(rType == null || rType.trim().length() == 0) return booleanBuilder;

        BooleanBuilder conditions = new BooleanBuilder();
        if(rType.contains("t"))
            conditions.or(qUserBoard.title.contains(rKey));
        if(rType.contains("c"))
            conditions.or(qUserBoard.content.contains(rKey));
        if(rType.contains("u"))
            conditions.or(qUserBoard.user.name.contains(rKey));
        booleanBuilder.and(conditions);

        return booleanBuilder;
    }

    private Function<UserBoard, UserBoardDto> function = (entity) -> entityToDto(entity);


    //생성자 기반 DI
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

}
