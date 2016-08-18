package com.yc.wowo.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.biz.impl.AdminInfoBizImpl;
import com.yc.wowo.entities.AdminInfo;
import com.yc.wowo.entities.Mail;
import com.yc.wowo.utils.AttributeData;
import com.yc.wowo.utils.MailUtil;
import com.yc.wowo.utils.UploadUtil;

public class AdminInfoServlet extends BasicServlet{
	private static final long serialVersionUID = -1895688370031862751L;
//	private String rcode = getRcode(); //写在外面 30分钟内  验证码不会变  线程安全问题

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String op = request.getParameter("op");

		if("adminLogin".equals(op)){//判断登录用户的账号密码
			adminLogin(request,response);
		}else if("getLoginInfo".equals(op)){//返回登录对象
			getLoginInfo(request,response);
		}else if("findAdminInfoByPage".equals(op)){
			findAdminInfoByPage(request, response);
		}else if("addAdminInfo".equals(op)){
			addAdminInfo(request, response);
		}else if("updateAdminInfo".equals(op)){
			updateAdminInfo(request,response);
		}else if("searchAdminInfoByPage".equals(op)){
			searchAdminInfoByPage(request,response);
		}else if("retrievePassword".equals(op)){
			retrievePassword(request,response);
		}else if("testRcode".equals(op)){
			testRcode(request,response);
		}else if("newPassword".equals(op)){
			newPassword(request, response);
		}
	}

	/**
	 * 多条件查询
	 * @param request
	 * @param response
	 */
	private void searchAdminInfoByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String rid = request.getParameter("rid");
		String aname = request.getParameter("aname");
		String status = request.getParameter("status");
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("rid", rid);
		map.put(" aname like ", "%"+aname+"%");
		map.put("status", status);
		
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		List<AdminInfo> list =  ab.find(map, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<AdminInfo> list1 = ab.find(map, null, null);
		this.out(response, list, list1.size());
	}

	/**
	 * 修改管理员信息
	 * @param request
	 * @param response
	 */
	private void updateAdminInfo(HttpServletRequest request,
			HttpServletResponse response) {
		UploadUtil upload = new UploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		Map<String,String> map = upload.upload(pageContext);
		String aname = map.get("aname");
		String rid = map.get("rid");
		String tel = map.get("tel");
		String photo = map.get("photo");
		String aid = map.get("aid");
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		this.out(response, (int)ab.update(aname, rid, tel, photo, aid));
	}

	/**
	 * 添加管理员信息
	 * @param request
	 * @param response
	 */
	private void addAdminInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String rid = request.getParameter("rid");
		String aname = request.getParameter("aname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String pic = request.getParameter("photo");
		
		if(pic!=null||"".equals(pic)){
			UploadUtil upload = new UploadUtil();
			PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
			pic = upload.upload(pageContext, pic, null);
		}else{
			pic="";
		}
		IAdminInfoBiz adminInfo = new AdminInfoBizImpl();
		this.out(response, (int)adminInfo.add(aname, password, rid, email, tel, pic));
	}

	/**
	 * 分页查询管理员信息
	 * @param request
	 * @param response
	 */
	private void findAdminInfoByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		List<AdminInfo> list = ab.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, ab.getTotal(null));
	}

	/**
	 * 获取当前用户信息
	 * @param request
	 * @param response
	 */
	private void getLoginInfo(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(AttributeData.CURRENTADMINLOGIN);
		if(obj==null){//说明用户没有登录
			this.out(response, obj);
		}else{
			this.out(response, (AdminInfo)obj);
		}
	}

	/**
	 * 后台管理员登录
	 * @param request
	 * @param response
	 */
	private void adminLogin(HttpServletRequest request,HttpServletResponse response) {
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		
		//在访问业务层之前，先可以验证验证码   获取session
		 HttpSession session = request.getSession();
		 String codes = (String) session.getAttribute("rands");
		 int result = 0;
		 if(code.equals(codes)){//验证码成功，调用业务层
			 IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
			 AdminInfo adminInfo = adminInfoBiz.login(name, pwd, role);
			 if(adminInfo == null){ //说明用户 密码错误
				 result = 2;
			 }else{//登录成功
				 result = 3;
				 session.setAttribute(AttributeData.CURRENTADMINLOGIN, adminInfo);
			 }
		 }else{//验证码错误
			 result = 1;
		 }
		this.out(response, result);
	}
	
	/**
	 * 验证用户名和邮箱 并发送验证码 将验证码存进session
	 * @param request
	 * @param response
	 */
	private void retrievePassword(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		try {
			email=URLDecoder.decode(email,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		System.out.println(email);
		
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		
		//result == 0 username email为空  或者 数据库没有该信息
		int result = ab.getTotal(username, email);
		if(result == 0){
			this.out(response, 0);
			return;
		}
		Mail mail = new Mail();
		String rcode = getRcode();
		mail.setMessage(mail.getMessage()+rcode);
		mail.setReceiver(email);
		MailUtil mu = new MailUtil();
		boolean status = mu.send(mail);
		
		if(!status){
			this.out(response, 0);
			return;
		}
		setRcodeSession(request, response, rcode);
		this.out(response, 1);
	}
	
	/**
	 * 验证验证码
	 * @param request
	 * @param response
	 */
	private void testRcode(HttpServletRequest request,
			HttpServletResponse response) {
		String rcode = request.getParameter("rcode");
		String sessionCode = (String) request.getSession().getAttribute("sessionCode");
		int result = 0;
		if(rcode == null || "".equals(rcode)){ // 输入的验证码不能为空
			result = 0 ;
		}else if(sessionCode == null || "".equals(sessionCode)){ // 未发送邮件 session 验证码过期
			result = 1;
		}else if(!sessionCode.equals(rcode)){ // 验证码错误
			result = 2;
		}else if(sessionCode.equals(rcode)){ // 验证成功
			result = 3;
		}
		this.out(response, result);
	}
	
	/**
	 * 创建session 存验证码
	 * @param request
	 * @param response
	 */
	private void setRcodeSession(HttpServletRequest request,
			HttpServletResponse response,String rcode) {
		HttpSession session = request.getSession();
        String sessionId = session.getId();
        Cookie cookie = new Cookie("JSESSIONID", sessionId);

        cookie.setPath("/wowo/");//设置session的有效路径，覆盖默认的session的路径
        cookie.setMaxAge(10*60);//设置时长为10分钟
        response.addCookie(cookie);

        session.setAttribute("sessionCode", rcode);
	}

	/**
	 * 获取随机验证码
	 * @return rcode
	 */
	private String getRcode() {
		Random random = new Random();
		String rcode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			rcode += rand;
		}
		return rcode;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 */
	private void newPassword(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		try {
			email=URLDecoder.decode(email,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		IAdminInfoBiz ab = new AdminInfoBizImpl();
		int result = ab.update1(email, pwd);
		this.out(response, result);
	}
}
