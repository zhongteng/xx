package com.xx.tech.framework.core.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.tech.framework.core.message.enums.MessageType;

/**
 * 消息建造者类
 * @author benjamin
 *
 */
public class MessageBuilder {
	
	private List<RequestMessage> requests = new ArrayList<>();
	
	private List<ResponseMessage> responses = new ArrayList<>();
	
	private static final Object synLock = new Object();

	private static MessageBuilder instance = null;

	private MessageBuilder() {

	}

	public static MessageBuilder getInstance() {
		if (instance == null) {
			synchronized (synLock) {
				if (instance == null) {
					instance = new MessageBuilder();
				}
			}
		}
		return instance;
	}
	
	/**
	 * RequestMessage建造者方法
	 * @param count
	 */
	public void produceRequests(int count) {
		for (int i = 0; i < count; i++) {
			requests.add(RequestMessage.newInstance());
		}
	}

	/**
	 * ResponseMessage建造者方法
	 * @param count
	 */
	public void produceResponses(int count) {
		for (int i = 0; i < count; i++) {
			responses.add(ResponseMessage.newInstance());
		}
	}
	

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		MessageFactory factory = MessageFactory.getInstance();
		Message request = factory.produce(MessageType.REQUEST);
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
		
		Message response = factory.produce(MessageType.RESPONSE);
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));
		
		MessageBuilder builder = MessageBuilder.getInstance();
		builder.produceRequests(10);
		List<RequestMessage> requests = builder.requests;
		System.out.println(new ObjectMapper().writeValueAsString(requests));
		
		builder.produceResponses(10);
		List<ResponseMessage> responses = builder.responses;
		System.out.println(new ObjectMapper().writeValueAsString(responses));
	}
}
