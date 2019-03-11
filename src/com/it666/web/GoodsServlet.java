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
		//1.���÷����
		GoodsService goodService = new GoodsService();
		try {
			List<Goods> allGoods = goodService.getAllGoods();
			Collections.reverse(allGoods);
			//������д��request����
			request.setAttribute("allGoods", allGoods);
			//ת��
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
            //��ȡ��ǰҳ��
            String currentPage = request.getParameter("currentPage");
            //��ҳ�뽻��ҵ���
            GoodsService goodsService = new GoodsService();
            PageBean pageBean = goodsService.getPageBean(Integer.parseInt(currentPage));
            //��pageBeanд������
            request.setAttribute("pageBean",pageBean);
            //ת��
            return "admin/main.jsp";
        }catch (Exception e){
	        e.printStackTrace();
        }
	    return null;
    }
	
	public String delGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.����id����
		String id = request.getParameter("id");
		
		//2.���÷����
		GoodsService goodService = new GoodsService();
		try {
			goodService.deleteGoods(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/GoodsListServlet?action=getListGoods";
		//ת��
		//request.getRequestDispatcher("/GoodsListServlet").forward(request, response);;
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���в���
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		//�Ѳ�����װ�ɶ���
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_11.jpg");
			//���÷����
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//System.out.println(goods);
			//��ת�б�ȡ�����µ�����
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
		//��ȡ���в���
		Map<String, String[]> parameterMap = request.getParameterMap();
		//��װ��Goods����
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_12.png");
			//����id��������
			GoodsService goodsService = new GoodsService();
			goodsService.updateGoods(goods);
			//��ת����ҳ
			//request.getRequestDispatcher("/GoodsListServlet").forward(request, response);
			return "/GoodsListServlet?action=getListGoods";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//request.getRequestDispatcher("admin/edit.jsp").forward(request, response);
			return "admin/edit.jsp";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.ȡ�����з���
		CategoryService categoryService = new CategoryService();
		try {
			//2.�ѷ���浽����
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			//System.out.println(allCategory);
			//3.ת����add.jsp
			return "admin/add.jsp";
			//request.getRequestDispatcher("admin/add.jsp").forward(request, response);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
