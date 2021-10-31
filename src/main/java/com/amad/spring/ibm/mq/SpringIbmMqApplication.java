package com.amad.spring.ibm.mq;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.EnableJms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableJms
public class SpringIbmMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIbmMqApplication.class, args);
	}

	@EventListener(classes = ApplicationStartedEvent.class)
	public void startUp() throws JMSException {
		log.info("Application Started Event Captured!");
	}
}
