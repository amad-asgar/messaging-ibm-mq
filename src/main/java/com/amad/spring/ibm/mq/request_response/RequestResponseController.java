package com.amad.spring.ibm.mq.request_response;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.jms.JMSException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amad.spring.ibm.mq.request_response.messages.Order;
import com.amad.spring.ibm.mq.request_response.messages.Shipment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class RequestResponseController {

	private final JmsRequestResponsePattern jmsRequestResponsePattern;

	@GetMapping("/send-with-reply")
	public ResponseEntity<Shipment> getSendWithReply() throws JMSException {
		Shipment shipment = jmsRequestResponsePattern
				.sendWithReplyMessage(new Order(UUID.randomUUID(), "customerNameTest", LocalDateTime.now()));

		log.info("Shipment is {}", shipment);
		return ResponseEntity.ok(shipment);
	}
}
