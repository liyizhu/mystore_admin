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
		//��ȡҪ�޸ĵ���Ʒid
		String id = request.getParameter("id");
		
		GoodsService goodsService = new GoodsService();
		try {
			Goods goods = goodsService.getGoodsWithId(id);
			//����Ʒд������
			request.setAttribute("goods", goods);
			
			//��ȡ���з���
			CategoryService categoryService = new CategoryService();
			
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			//ת��
			request.getRequestDispatcher("admin/edit.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
