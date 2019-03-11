package com.it666.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it666.domain.PageBean;
import org.apache.commons.beanutils.BeanUtils;

import com.it666.domain.Category;
import com.it666.domain.Goods;
import com.it666.service.CategoryService;
import com.it666.service.GoodsService;


@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String getListGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.调用服务层
		GoodsService goodService = new GoodsService();
		try {
			List<Goods> allGoods = goodService.getAllGoods();
			Collections.reverse(allGoods);
			//把数据写到request域中
			request.setAttribute("allGoods", allGoods);
			//转发
			return "admin/main.jsp";
			//request.getRequestDispatcher("/admin/main.jsp").forward(request, response);
			//System.out.println(allGoods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    public String getPageData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    try{
            //获取当前页码
            String currentPage = request.getParameter("currentPage");
            //把页码交给业务层
            GoodsService goodsService = new GoodsService();
            PageBean pageBean = goodsService.getPageBean(Integer.parseInt(currentPage));
            //把pageBean写到域中
            request.setAttribute("pageBean",pageBean);
            //转发
            return "admin/main.jsp";
        }catch (Exception e){
	        e.printStackTrace();
        }
	    return null;
    }
	
	public String delGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接受id参数
		String id = request.getParameter("id");
		
		//2.调用服务层
		GoodsService goodService = new GoodsService();
		try {
			goodService.deleteGoods(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/GoodsListServlet?action=getListGoods";
		//转发
		//request.getRequestDispatcher("/GoodsListServlet").forward(request, response);;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取所有参数
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		//把参数封装成对象
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_11.jpg");
			//调用服务层
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//System.out.println(goods);
			//跳转列表，取出最新的数据
			return "/GoodsListServlet?action=getListGoods";
			//request.getRequestDispatcher("/GoodsListServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取所有参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		//封装成Goods对象
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_12.png");
			//根据id更新数据
			GoodsService goodsService = new GoodsService();
			goodsService.updateGoods(goods);
			//跳转回首页
			//request.getRequestDispatcher("/GoodsListServlet").forward(request, response);
			return "/GoodsListServlet?action=getListGoods";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//request.getRequestDispatcher("admin/edit.jsp").forward(request, response);
			return "admin/edit.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.取出所有分类
		CategoryService categoryService = new CategoryService();
		try {
			//2.把分类存到域中
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			//System.out.println(allCategory);
			//3.转发到add.jsp
			return "admin/add.jsp";
			//request.getRequestDispatcher("admin/add.jsp").forward(request, response);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
