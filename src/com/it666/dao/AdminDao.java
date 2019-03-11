package com.it666.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it666.domain.Admin;
import com.it666.utils.JdbcUtil;

public class AdminDao {

	public Admin checkAdmin(String name, String pwd) throws SQLException {
		//�����ݿ��в�ѯ�����ز�ѯ���
		//����
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		//��ѯ
		String sql = "select * from admin where username=? and password=?";
		//ִ�в�ѯ
		Admin admin = null;
		
		admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),name,pwd);
		
		
		return admin;
	}

}
