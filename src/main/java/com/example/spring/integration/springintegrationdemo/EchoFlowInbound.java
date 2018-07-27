package com.example.spring.integration.springintegrationdemo;

import java.util.function.Consumer;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.GenericEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.SubscribableChannel;

@Configuration

public class EchoFlowInbound {

	@Bean
	public IntegrationFlow inboundFlow() {
		return IntegrationFlows.from(Jms
				.inboundGateway(connectionFactory)
				.destination("amq.outbound"))
				.transform((String s) -> s.toUpperCase())
				.log()
				.channel(subscribableChannel1)
				.bridge()
				//
				.get();
/*		return IntegrationFlows.from(subscribableChannel1)
        .handle(Jms.inboundAdapter(connectionFactory)
                .destination("amq.outbound")).transform((String s) -> s.toUpperCase())                            
        .get();*/
		//https://stackoverflow.com/questions/51044168/spring-integration-jms-assured-message-delivery-using-dsl
		//https://dzone.com/articles/spring-integration-java-dsl-0
		//https://stackoverflow.com/questions/44910958/spring-integration-http-outbound-gateway-post-request-with-java-dsl
		//https://stackoverflow.com/questions/34065163/outbound-channel-adapter-vs-outbound-gateway-for-http-communication
		//https://xpadro.com/2016/11/spring-integration-mongodb-adapters-with-java-dsl.html
		//https://stackoverflow.com/questions/34978468/http-outbound-channel-adapter-with-basic-authentication
		//https://stackoverflow.com/questions/32073381/spring-integration-dsl-creating-jms-outbound-adapter-in-java-1-7
		//https://stackoverflow.com/questions/27897941/spring-integration-jms-publish-subscribe-in-both-inbound-and-outbound
		//https://stackoverflow.com/questions/18315839/spring-integration-http-gateway-and-jms
		//https://dzone.com/articles/spring-integration-jms-and
	}

	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private SubscribableChannel subscribableChannel1;
	@Autowired
	private QueueChannel jmsOutChannel;
}
