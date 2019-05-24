package com.jbwz.core.common.base;

import java.util.ArrayList;
import java.util.List;

/**
 * common pageList
 *
 * @author yyh
 */
public class PageList<T> extends Page {

    private static final long serialVersionUID = -1234509938956089562L;
    private List<T> content = new ArrayList();

    public PageList(long total, List<T> content, int pageSize, int pageNum) {
        this.total = total;
        this.content = content;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public PageList(long total, List<T> content) {
        this.total = total;
        this.content = content;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
