package com.yc.wowo.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import sun.misc.BASE64Decoder;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class UploadUtil {
	public static String PATH = "../upload";
	private static final String ALLOWED = "gif,jpg,png,txt,doc,xls"; //文件上传的文件类型
	private static final String DENIDE = "exe,bat,jsp,html"; //不允许上传的文件类型
	private static final int SINGLEFILESIZE = 2*1024*1024; //单个文件最大大小
	private static final int TOTALMAXSIZE = 20*1024*1024; //每次上传的总大小
	
	public Map<String,String> upload(PageContext pageContext){
		Map<String,String> map = new HashMap<String, String>();
		SmartUpload upload = new SmartUpload();
		
		try {
			//初始化
			upload.initialize(pageContext);
			upload.setDeniedFilesList(DENIDE);
			upload.setAllowedFilesList(ALLOWED);
			upload.setMaxFileSize(SINGLEFILESIZE);
			upload.setTotalMaxFileSize(TOTALMAXSIZE);
			upload.setCharset("utf-8");
			
			//开始上传
			upload.upload();
			
			Request request = upload.getRequest();
			
			//取出所有的普通表单元素，并将其存入到map中
			Enumeration<String> names = request.getParameterNames();
			
			String str = null;
			while( names.hasMoreElements() ){
				str=names.nextElement();
				map.put(str, request.getParameter(str));
			}
			
			//获取所有要上传的文件
			Files files = upload.getFiles();
			
			//说明确实有文件要上传
			if(files != null && files.getCount() > 0){
				WatermarkUtil util = new WatermarkUtil();
				Collection<File> cols = files.getCollection();
				String fname = null;//文件名
				String fpath = "";
				String fieldName = null;
				
				for( File file : cols){
					
					if( !file.isMissing() ){//如果上传的时候没有丢失数据                                                                        后缀名
						fname = new Date().getTime()+""+new Random().nextInt(1000)+"."+file.getFileExt();
						
						String filePath = pageContext.getServletContext().getRealPath(PATH+"/"+fname);
						
						file.saveAs(filePath);//保存
						util.mark(filePath, filePath, Color.red, "源辰信息");
						
						fpath+=PATH+"/"+fname+",";//拼接字符串路径
						fieldName=file.getFieldName();//获取表单元素名
					}
					
				}
				
				if(fieldName != null){
					if(fpath.contains(",")){
						fpath = fpath.substring(0,fpath.lastIndexOf(","));
					}
					map.put(fieldName, fpath);
				}
			}
		} catch (ServletException | IOException | SQLException
				| SmartUploadException e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
	
	/**
	 * 
	 * @param picData 图片数据
	 * @param path 要保存的文件路径
	 * @return 图片上传后的路径
	 */
	public String upload(PageContext pageContext, String picData,String path){
		String realpath = null;
		BASE64Decoder base64 = new BASE64Decoder();
		FileOutputStream fos = null;
		try {
			byte[] buffer = base64.decodeBuffer(picData);  //将图片字符串变成字节数据

			if(path == null){
				//将字节数组数据写入图片文件
				String fname = new Date().getTime()+""+new Random().nextInt(1000)+".png";
				String filePath = pageContext.getServletContext().getRealPath(PATH+"/"+fname);
				fos = new FileOutputStream(filePath);
				realpath = PATH+"/"+fname;
			}else{
				fos = new FileOutputStream(path);
				realpath=path;
			}
			
			fos.write(buffer);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return realpath;
	}
}
