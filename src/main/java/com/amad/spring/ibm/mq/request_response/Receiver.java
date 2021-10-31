package com.amad.spring.ibm.mq.request_response;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.amad.spring.ibm.mq.request_response.messages.Order;
import com.amad.spring.ibm.mq.request_response.messages.Shipment;
import com.ibm.jms.JMSObjectMessage;
import com.ibm.msg.client.jms.internal.JmsObjectMessageImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver implements SessionAwareMessageListener<Message> {

	public static final String ORDER_QUEUE = "order.queue";

	@JmsListener(destination = ORDER_QUEUE)
	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		log.info("Recieved Message: {}", message);
		Order order = (Order) ((JMSObjectMessage) message).getObject();
		Shipment shipment = new Shipment(UUID.randomUUID(), order.getOrderId(), LocalDateTime.now());

		// done handling the request, now create a response message
		ObjectMessage responseMessage = session.createObjectMessage();
		log.info("message.getJMSCorrelationID(): {}", message.getJMSCorrelationID());
		responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
		responseMessage.setObject(shipment);

		// Message sent back to the replyTo address of the income message.
		final MessageProducer producer = session.createProducer(message.getJMSReplyTo());
		producer.send(responseMessage);
	}

}
