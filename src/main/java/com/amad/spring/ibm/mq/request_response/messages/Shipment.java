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
public class Shipment implements Serializable {

	private static final long serialVersionUID = 2725615030953344817L;

	private UUID shipmentId;
	private UUID orderId;
	private LocalDateTime shipmentTime;
}
