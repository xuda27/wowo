package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.wowo.biz.IGoodsTypeBiz;
import com.yc.wowo.biz.IUserBiz;
import com.yc.wowo.biz.impl.GoodsTypeBizImpl;
import com.yc.wowo.biz.impl.UserBizImpl;
import com.yc.wowo.entities.GoodsType;
import com.yc.wowo.entities.UserInfo;
import com.yc.wowo.utils.AttributeData;

public class GoodsTypeServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
    public GoodsTypeServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if("findGoodsTypeByPage".equals(op)){
			findGoodsTypeByPage(request,response);
		}
		
	}

	/**
	 * 分页查询
	 * @param request
	 * @param response
	 */
	private void findGoodsTypeByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IGoodsTypeBiz gtb = new GoodsTypeBizImpl();
		List<GoodsType> list = gtb.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<GoodsType> rs = (List<GoodsType>) gtb.find();
		this.out(response, list, rs.size());
	}

}
