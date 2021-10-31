package com.amad.spring.ibm.mq.request_response.messages;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 8458664267624038342L;

	private UUID orderId;

	private String customerName;

	private LocalDateTime oderTime;
}
