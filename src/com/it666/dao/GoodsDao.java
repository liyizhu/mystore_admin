package com.it666.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.it666.domain.Goods;
import com.it666.utils.JdbcUtil;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class GoodsDao {
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	
	//1.�����ݿ��в�ѯ������Ʒ
	public List<Goods> getAllGoods() throws SQLException{
		//1.��ѯ����
		String sql = "select * from goods";
		
		//2.ִ��sql
		List<Goods> allGoods = null;
		allGoods = qr.query(sql, new BeanListHandler<Goods>(Goods.class));
		return allGoods;
	}
	
	//2.�����Ʒ�����ݿ�
	public void addGoods(Goods goods) throws SQLException {
		String sql = "insert into goods(name,price,image,descr,is_hot,cid) values(?,?,?,?,?,?)";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getDescr(),goods.getIs_hot(),goods.getCid());
	}
	
	//3.����idɾ����Ʒ
	public void delGoods(int id) throws SQLException {
		String sql = "delete from goods where id=?";
		qr.update(sql,id);
	}
	
	
	//4.������Ʒ
	public void updateGoods(Goods goods) throws SQLException {
		//���²���
		String sql = "update goods set name=?,price=?,image=?,descr=?,is_hot=?,cid=? where id=?";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getDescr(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}
	
	//����id��ѯһ����Ʒ
	public Goods getGoodsWithId(String id) throws Exception {
		String sql = "select * from goods where id=?";
		Goods goods = qr.query(sql, new BeanHandler<Goods>(Goods.class),Integer.parseInt(id));
		return goods;
	}

    public Long getCount() throws SQLException {
		String sql = "select count(*) from goods";
		Long count = (Long)qr.query(sql, new ScalarHandler());
		return count;
	}

	public List<Goods> getPageData(Integer index, Integer pageCount) throws SQLException {
		String sql = "select * from goods limit ?,?";
		List<Goods> pageGoods = qr.query(sql, new BeanListHandler<Goods>(Goods.class), index, pageCount);
		return pageGoods;
	}
}
