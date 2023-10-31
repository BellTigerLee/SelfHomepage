package ugps.myweb.gpsinside.Dto;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PageResponseDto<DTO, EN> {
    private List<DTO> content;
    private int page;
    private int size;
    private int start, end;
    private long totalElements;
    private int totalPages;
    private List<Integer> pageList;
    private boolean prev, last;

    public PageResponseDto(Page<EN> content_list, Function<EN, DTO> func) {
        content = content_list.stream().map(func).collect(Collectors.toList());
        totalPages = content_list.getTotalPages();
        makePageList(content_list.getPageable());
    }

//    public List<DTO> getContent() {
//        return content;
//    }
//
//    public List<Integer> getPageList() {
//        return pageList;
//    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isLast() {
        return last;
    }

    private void makePageList(Pageable pg) {
        this.page = pg.getPageNumber() + 1;
        this.size = pg.getPageSize();
        int tmpEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tmpEnd - 9;
        end = totalPages > tmpEnd ? tmpEnd : totalPages;
        prev = start > 1;
        last = totalPages > tmpEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "------------------\n" +
                "start : " + start + ", end : " + end + '\n' +
                "page : " + page + '\n' +
                "size : " + size + '\n' +
                "totalElements : " + totalElements + '\n' +
                "totalPages : " + totalPages + '\n' +
                "Prev : " + prev + '\n' +
                "Last : " + last + '\n' +
                "------------------\n";
    }

}