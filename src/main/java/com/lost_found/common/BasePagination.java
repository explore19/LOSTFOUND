package com.lost_found.common;

public class BasePagination {


    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        if(page == null){
            page = Const.DEFAULT_PAGE;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if(pageSize == null){
            pageSize =Const.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public Integer getOffSet() {
        return (getPage()-1)*getPageSize();
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
