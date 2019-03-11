package com.it666.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.it666.dao.GoodsDao;
import com.it666.domain.Goods;

public class GoodsDaoTest {
	private GoodsDao goodsDao = new GoodsDao();
	
	@Test
	public void testGetAllGoods() throws SQLException {
		List<Goods> allGoods = goodsDao.getAllGoods();
		System.out.println(allGoods);
	}
	
	@Test
	public void testAddGoods() throws SQLException {
		Goods goods = new Goods();
		goods.setName("¿Óﬁ»÷˘1111");
		goods.setPrice(35.5);
		goods.setImage("goods_10.png");
		goods.setDescr("666");
		goods.setIs_hot(1);
		goods.setCid(5);
		goodsDao.addGoods(goods);
	}
	
	@Test
	public void testDddGoods() throws SQLException {
		goodsDao.delGoods(16);
	}
	
	@Test
	public void testUpdateGoods() throws SQLException {
		Goods goods = new Goods();
		goods.setId(20);
		goods.setName("myxq2222");
		goods.setPrice(40.0);
		goods.setImage("goods_11.png");
		goods.setDescr("777");
		goods.setIs_hot(1);
		goods.setCid(5);
		goodsDao.updateGoods(goods);
	}
}
