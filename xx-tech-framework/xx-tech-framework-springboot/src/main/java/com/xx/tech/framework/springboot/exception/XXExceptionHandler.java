package com.xx.tech.framework.springboot.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class XXExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(XXExceptionHandler.class);

	@ExceptionHandler
	public String handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		Enumeration<String> ss = request.getHeaderNames();
		StringBuffer sb = new StringBuffer();
		while (ss.hasMoreElements()) {
			String key = ss.nextElement();
			sb.append("[").append(key).append(":").append(request.getHeader(key)).append("]");
		}
		logger.info(sb.toString());

		// printException(e);// log4j打印错误级别堆栈日志信息，时间
		// json格式的ajax请求
		if (request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
			response.setStatus(500);
			response.setContentType("application/json;charset=utf-8");
			try {
				PrintWriter writer = response.getWriter();
				if (e instanceof RuntimeException) {// 运行时异常
					writer.write("系统内部异常！");
				} else {// 非运行时异常
					writer.write(e.getMessage());// 此处待细化异常处理给提示 ？？？
				}
				writer.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} else {// URL普通请求
			if (e instanceof RuntimeException) {// 运行时异常
				request.setAttribute("exceptionMessage", "系统内部异常！");
			} else {
				request.setAttribute("exceptionMessage", e.getMessage());// 此处待细化异常处理给显示
			}
			try {// 跳转统一异常处理界面
				request.getRequestDispatcher("../error.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	private boolean isAjax(HttpServletRequest request) {
		boolean xAjax = false;
		
		return xAjax;
	}

	public void printException(Exception e) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			// 将出错的栈信息输出到printWriter中
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		logger.info(new Date() + ":" + sw.toString());
	}

}
