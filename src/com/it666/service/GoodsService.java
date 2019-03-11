package com.it666.service;

import java.sql.SQLException;
import java.util.List;

import com.it666.dao.GoodsDao;
import com.it666.domain.Goods;
import com.it666.domain.PageBean;

public class GoodsService {
	GoodsDao goodsDao = new GoodsDao();
	public List<Goods> getAllGoods() throws SQLException {
		//从数据库中获取所有数据
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
		//设置当前页
		pageBean.setCurrentPage(parseInt);
		//调用dao查询总记录数
		Long count = goodsDao.getCount();
		pageBean.setTotalCount(count.intValue());
		//一页显示多少条数据
		Integer pageCount = 5;
		//总页数
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
		pageBean.setTotalPage((int)totalPage);

		//limit当前页的开始序号为：（当前页-1）* 每页的记录数
		Integer index = (pageBean.getCurrentPage()-1) * pageCount;
		//查询当前页的数据
		List<Goods> list = goodsDao.getPageData(index,pageCount);
		pageBean.setGoodsList(list);

		return pageBean;
    }
}
