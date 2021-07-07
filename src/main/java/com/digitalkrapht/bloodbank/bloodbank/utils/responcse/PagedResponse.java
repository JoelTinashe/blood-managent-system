package com.digitalkrapht.bloodbank.bloodbank.utils.responcse;

import java.util.Collection;

public class PagedResponse<T>{
    public Collection<T> content;
    public int page;
    public int size;
    public long totalElements;
    public int totalPages;
    public boolean last;

    public PagedResponse() {

    }

    public PagedResponse(Collection<T> content, int page, int size, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

}
