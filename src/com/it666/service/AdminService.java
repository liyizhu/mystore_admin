package com.it666.service;


import com.it666.dao.AdminDao;
import com.it666.domain.Admin;

public class AdminService {

	public Admin login(String name, String pwd) throws Exception {
		//调用dao到数据库查询
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.checkAdmin(name,pwd);
		if(admin!=null) {
			return admin;
		}else {
			throw new Exception("用户名或密码错误");
		}
	}
	
}
