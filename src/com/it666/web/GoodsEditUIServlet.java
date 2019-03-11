package com.it666.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it666.domain.Category;
import com.it666.domain.Goods;
import com.it666.service.CategoryService;
import com.it666.service.GoodsService;


@WebServlet("/GoodsEditUIServlet")
public class GoodsEditUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要修改的商品id
		String id = request.getParameter("id");
		
		GoodsService goodsService = new GoodsService();
		try {
			Goods goods = goodsService.getGoodsWithId(id);
			//把商品写到域中
			request.setAttribute("goods", goods);
			
			//获取所有分类
			CategoryService categoryService = new CategoryService();
			
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			//转发
			request.getRequestDispatcher("admin/edit.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
