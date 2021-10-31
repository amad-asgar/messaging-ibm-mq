package com.amad.spring.ibm.mq.listeners;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MQListener {

	@JmsListener(destination = "DEV.QUEUE.1")
	public void onMessage(Message message) throws JMSException {
		log.info("Recieved Message is :" + message.getBody(String.class));
	}
}
