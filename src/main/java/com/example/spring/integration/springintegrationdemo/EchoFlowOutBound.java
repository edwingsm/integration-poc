package com.example.spring.integration.springintegrationdemo;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

@Configuration

public class EchoFlowOutBound {

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public IntegrationFlow toOutboundQueueFlow() {
		return IntegrationFlows.from("requestChannel")
				
				.handle(Jms.outboundGateway(connectionFactory)
						.requestDestination("amq.outbound"))
			
				.get();
	}
}