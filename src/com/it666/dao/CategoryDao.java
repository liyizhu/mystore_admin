package com.it666.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.it666.domain.Category;
import com.it666.utils.JdbcUtil;

public class CategoryDao {

	public List<Category> getAllCategory() throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		List<Category> allCategory = null;
		allCategory = qr.query("select * from category", new BeanListHandler<Category>(Category.class));
		return allCategory;
		
	}

}
