package com.it666.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it666.domain.Admin;
import com.it666.utils.JdbcUtil;

public class AdminDao {

	public Admin checkAdmin(String name, String pwd) throws SQLException {
		//到数据库中查询，返回查询结果
		//连接
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		//查询
		String sql = "select * from admin where username=? and password=?";
		//执行查询
		Admin admin = null;
		
		admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),name,pwd);
		
		
		return admin;
	}

}
