package ugps.myweb.gpsinside.Dto;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageRequestDto {
    private int page;
    private int size;

    public PageRequestDto(int page) {
        this.page = page;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
