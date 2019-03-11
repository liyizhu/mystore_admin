package com.it666.service;

import java.sql.SQLException;
import java.util.List;

import com.it666.dao.GoodsDao;
import com.it666.domain.Goods;
import com.it666.domain.PageBean;

public class GoodsService {
	GoodsDao goodsDao = new GoodsDao();
	public List<Goods> getAllGoods() throws SQLException {
		//�����ݿ��л�ȡ��������
		List<Goods> allGoods = goodsDao.getAllGoods();
		return allGoods;	
	}

	public void deleteGoods(String id) throws Exception {
		goodsDao.delGoods(Integer.parseInt(id));
	}

	public void addGoods(Goods goods) throws SQLException {
		goodsDao.addGoods(goods);
	}

	public Goods getGoodsWithId(String id) throws Exception {
		Goods goods = goodsDao.getGoodsWithId(id);
		return goods;
	}

	public void updateGoods(Goods goods) throws SQLException {
		goodsDao.updateGoods(goods);
	}

    public PageBean getPageBean(Integer parseInt) throws SQLException {
		PageBean pageBean = new PageBean();
		//���õ�ǰҳ
		pageBean.setCurrentPage(parseInt);
		//����dao��ѯ�ܼ�¼��
		Long count = goodsDao.getCount();
		pageBean.setTotalCount(count.intValue());
		//һҳ��ʾ����������
		Integer pageCount = 5;
		//��ҳ��
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
		pageBean.setTotalPage((int)totalPage);

		//limit��ǰҳ�Ŀ�ʼ���Ϊ������ǰҳ-1��* ÿҳ�ļ�¼��
		Integer index = (pageBean.getCurrentPage()-1) * pageCount;
		//��ѯ��ǰҳ������
		List<Goods> list = goodsDao.getPageData(index,pageCount);
		pageBean.setGoodsList(list);

		return pageBean;
    }
}
