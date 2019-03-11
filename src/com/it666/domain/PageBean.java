package com.it666.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class PageBean {
    //��ǰ����һҳ
    private Integer currentPage;
    //������ҳ
    private Integer totalPage;
    //����������¼
    private Integer totalCount;
    //��ǰҲ��Ʒ
    private List<Goods> goodsList = new ArrayList<>();
}
