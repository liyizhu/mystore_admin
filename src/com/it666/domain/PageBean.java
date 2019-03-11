package com.it666.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class PageBean {
    //当前是哪一页
    private Integer currentPage;
    //共多少页
    private Integer totalPage;
    //共多少条记录
    private Integer totalCount;
    //当前也商品
    private List<Goods> goodsList = new ArrayList<>();
}
