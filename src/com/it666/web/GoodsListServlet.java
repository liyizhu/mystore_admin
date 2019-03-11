package com.it666.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it666.domain.Goods;
import com.it666.service.GoodsService;



@WebServlet("/GoodsListServlet")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.调用服务层
		GoodsService goodService = new GoodsService();
		try {
			List<Goods> allGoods = goodService.getAllGoods();
			Collections.reverse(allGoods);
			//把数据写到request域中
			request.setAttribute("allGoods", allGoods);
			//转发
			request.getRequestDispatcher("/admin/main.jsp").forward(request, response);
			System.out.println(allGoods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
   
}
