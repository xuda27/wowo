package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.wowo.entities.Mail;
import com.yc.wowo.utils.MailUtil;

public class SendMailServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
    public SendMailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String mail = request.getParameter("mail");
		
		List<String> list = new LinkedList<String>();
		Collections.addAll(list, "1","2","3","4","5","6","7","8","9");
		for(int i = 97; i < 123; i++){
			list.add( String.valueOf((char)i) );
		}
		
		//打乱顺序
		Collections.shuffle(list);
		
		String code = "";
		for(int i = 0; i<6; i++){
			code+=list.get(i);
		}
		
		session = request.getSession();
		session.setAttribute("sessionCode", code);
		
		Mail email = new Mail();
		email.setMessage(email.getMessage()+code);
		email.setReceiver("xuda27@qq.com");
		MailUtil mailUtil = new MailUtil();
		boolean  bl = mailUtil.send(email);
		
		if(bl){
			this.out(response, 1);
		}else{
			this.out(response, 0);
		}
		
		startTimer();
	}

	private void startTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				session.removeAttribute("sendCode");
				timer.cancel();
			}
		}, 10000);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
