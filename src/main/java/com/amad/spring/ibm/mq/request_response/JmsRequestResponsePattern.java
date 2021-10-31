package com.amad.spring.ibm.mq.request_response;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.amad.spring.ibm.mq.request_response.messages.Order;
import com.amad.spring.ibm.mq.request_response.messages.Shipment;
import com.ibm.mq.jms.MQQueue;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class JmsRequestResponsePattern {

	private final JmsMessagingTemplate jmsMessagingTemplate;

	private final JmsTemplate jmsTemplate;

	public static final String ORDER_QUEUE = "order.queue";
	public static final String ORDER_REPLY_2_QUEUE = "order.reply.2.queue";

	public Shipment sendWithReplyMessage(Order order) throws JMSException {

		jmsTemplate.setReceiveTimeout(1000l);

		jmsMessagingTemplate.setJmsTemplate(jmsTemplate);

		Session session = jmsMessagingTemplate.getConnectionFactory().createConnection().createSession(true,
				Session.AUTO_ACKNOWLEDGE);

		ObjectMessage objectMessage = session.createObjectMessage(order);

		objectMessage.setJMSCorrelationID(UUID.randomUUID().toString());
		objectMessage.setJMSDestination(new MQQueue(ORDER_QUEUE));
		objectMessage.setJMSReplyTo(new MQQueue(ORDER_REPLY_2_QUEUE));
		objectMessage.setJMSExpiration(1000L);
		log.info("objectMessage.getJMSDeliveryMode(): {}" + objectMessage.getJMSDeliveryMode());

		Shipment shipment = jmsMessagingTemplate.convertSendAndReceive(new MQQueue(ORDER_QUEUE), objectMessage,
				Shipment.class);
		log.info("shipment: " + shipment);
		return shipment;

	}
}
