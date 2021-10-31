package com.amad.spring.ibm.mq.controller;

import javax.jms.Destination;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.msg.client.jms.admin.JmsDestinationImpl;
import com.ibm.msg.client.jms.internal.JmsMessageImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MqSpringController {

	private final JmsTemplate jmsTemplate;

	@GetMapping("/send")
	public String send() {
		try {
			jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
			return "OK";
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
	
	@GetMapping("/recv")
	public String recv(){
	    try{
	        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
	    }catch(JmsException ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}
	
	
	/*
	 * @GetMapping("/send/test") public String testSend() { try {
	 * jmsTemplate.sendAndReceive(new JmsDestinationImpl("DEV.QUEUE.TEST"), new
	 * JmsMessageImpl(arg0, arg1)) return "OK"; } catch (JmsException ex) {
	 * ex.printStackTrace(); return "FAIL"; } }
	 */
}
