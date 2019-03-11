package com.it666.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.it666.domain.Goods;
import com.it666.service.GoodsService;


@WebServlet("/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			request.getRequestDispatcher("/GoodsListServlet").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
