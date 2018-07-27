package com.johnny.atcrowdfunding.util;

import java.util.List;

/**
 * @author johnny
 * @create 2018-07-19 下午12:20
 **/
public class Page<T> {

    private int pageSize = 15;

    private List<T> content;

    private int pageNum;

    private int sumCount;

    private int prePageNum;

    private int sumPageNum = 1;

    private int lastPageNum;

    private int nextPageNum;

    private int firstPage = 1;


    public int getSumPageNum() {
        sumPageNum = getSumCount() % getPageSize() == 0 ? getSumCount() / getPageSize() :
                (getSumCount() / getPageSize() + 1);
        return sumPageNum;
    }

    public void setSumPageNum(int sumPageNum) {

        this.sumPageNum = sumPageNum;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNextPageNum() {
        nextPageNum = (sumPageNum > pageNum ? pageNum + 1 : pageNum);
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public int getPrePageNum() {
        prePageNum = pageNum - 1;
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", content=" + content +
                ", pageNum=" + pageNum +
                ", nextPageNum=" + nextPageNum +
                ", sumCount=" + sumCount +
                ", prePageNum=" + prePageNum +
                ", lastPageNum=" + lastPageNum +
                ", firstPage=" + firstPage +
                '}';
    }
}