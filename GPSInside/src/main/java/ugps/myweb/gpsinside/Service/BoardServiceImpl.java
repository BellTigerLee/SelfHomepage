package ugps.myweb.gpsinside.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ugps.myweb.gpsinside.Dto.UserBoardDto;
import ugps.myweb.gpsinside.Entity.UserBoard;
import ugps.myweb.gpsinside.Repository.BoardRepository;

import java.util.List;
import java.util.function.Function;

@Service
public class BoardServiceImpl implements BoardService{


    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<UserBoardDto> getBoardList() {
        Pageable sortedByBno = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<UserBoard> page = boardRepository.findAll(sortedByBno);
        List<UserBoardDto> dtoPage = page.get().map(function).toList();

        return dtoPage;
    }

    private Function<UserBoard, UserBoardDto> function = (entity) -> entityToDto(entity);
}
