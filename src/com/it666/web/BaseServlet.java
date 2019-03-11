package com.it666.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受参数
		String action = request.getParameter("action");
		
		try {
			//获取当前对象的字节码
			Class clazz = this.getClass();
			//获取方法
			Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			if(method!=null) {
				String desPath = (String)method.invoke(this, request,response);
				if(desPath!=null) {
					request.getRequestDispatcher(desPath).forward(request, response);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
