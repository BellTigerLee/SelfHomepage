package ugps.myweb.gpsinside.Dto;


import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageRequestDto {
    private int page;
    private int size;

    public PageRequestDto(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
