package com.bitcamp.gabojago.vo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageMakerDTO {
    int page; // 현재 페이지
    int size; // 출력할 게시글 개수
    int start;
    int end;
    boolean prev;
    boolean next;
    int total; // 총 페이지

    List<Recommendation> dtoList;

    /* 생성자 */

    public PageMakerDTO(int page, int size, int total, List<Recommendation> dtoList) {
        int lastPage = (int) Math.ceil(((double) total / (double) size));
        this.page = page;
        this.size = size;
        this.total = total;
        this.dtoList = dtoList;

        this.start = (((page / 2) * 2) + 1);
        this.end = Math.min(lastPage, (start + 1));
        this.prev = start !=1;
        this.next = lastPage > end;
    }
}
