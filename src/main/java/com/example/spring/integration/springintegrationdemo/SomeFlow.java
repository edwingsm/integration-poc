package com.example.spring.integration.springintegrationdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.SubscribableChannel;

public class SomeFlow {
	
	@Autowired
	private SubscribableChannel subscribableChannel1;
	
	@Bean
	public IntegrationFlow inboundFlow() {
		return IntegrationFlows.from(subscribableChannel1)
				.transform((String s) -> s.toLowerCase())
			//	.log()
			//	.bridge()
				//
				.get();
	}

}
