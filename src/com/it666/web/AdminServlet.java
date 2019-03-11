package com.it666.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.it666.domain.Admin;
import com.it666.service.AdminService;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		AdminService adminService = new AdminService();
		try {
			Admin admin = adminService.login(name,pwd);
			HttpSession session = request.getSession();
			//把用户保存到session
			session.setAttribute("admin", admin);
			//跳转到后台首页
			response.sendRedirect(request.getContextPath()+"/admin/admin_index.jsp");
		} catch (Exception e) {
			//e.printStackTrace();
			if(e.getMessage().equals("用户名或密码错误")) {
				//跳转回登陆页
				request.setAttribute("err", e.getMessage());
				//转发
				request.getRequestDispatcher("admin/admin_login.jsp").forward(request, response);
			}else {
				e.printStackTrace();
			}
			
		} 
	}

}
