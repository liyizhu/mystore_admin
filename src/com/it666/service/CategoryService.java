package com.it666.service;

import java.util.List;

import com.it666.dao.CategoryDao;
import com.it666.domain.Category;

public class CategoryService {
	//��ȡ���еķ�����Ϣ
	public List<Category> findCategory() throws Exception {
		CategoryDao categoryDao = new CategoryDao();
		List<Category> allCategory = categoryDao.getAllCategory();
		return allCategory;
	}

}
