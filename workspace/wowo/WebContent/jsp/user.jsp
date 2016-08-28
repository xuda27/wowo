<%@page import="com.yc.wowo.entities.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String getUserName(HttpServletRequest request) {
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if( user!=null ){
			return user.getUname();
		}else{
			return null;
		}
	}
	
%>
<%
	String uname = getUserName(request);
	out.write(uname);
%>