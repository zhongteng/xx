/*package com.xx.tech.framework.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class XXExceptionController {

	private static Logger logger = LoggerFactory.getLogger(XXExceptionController.class);

	@ExceptionHandler(JsonProcessingException.class)
	public @ResponseBody String exceptionProcess(HttpServletRequest request, HttpServletResponse response,
			RuntimeException ex) {
		System.out.println("exception!!!");
		return null;
	}

//	@ExceptionHandler(Exception.class)
//	public String duplicateSpittleHandler() {
//		return "error/duplicate";
//	}

	@ExceptionHandler({ Error.class, Exception.class, Throwable.class })
	public void exception(HttpServletRequest request, HttpServletResponse response, Throwable e) throws IOException {
		logger.error("Unknown error occurs during :{}", request.getRequestURI());
//		logger.error("Unknown system error stack:", e);
		outputMessage(response, 00221, "error msg");
	}

	private void outputMessage(HttpServletResponse response, long errCode, String errMsg) throws IOException {
		Map<String, String> result = new HashMap<>();
		result.put("errCode", String.valueOf(errCode));
		result.put("errMsg", errMsg);
		String json = new ObjectMapper().writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		ServletOutputStream os = response.getOutputStream();
		os.write(json.getBytes("utf-8"));
	}
}
*/