package com.example.spring.integration.springintegrationdemo;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableIntegration
@EnableJms
public class SpringIntegrationDemoApplication implements CommandLineRunner{
	@Autowired
	EchoGateway echoGateway;
	@Autowired
	QueueChannel jmsOutChannel;
	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String hi= echoGateway.echo("Something");
		//jmsOutChannel.send(message)
		System.out.println(hi);
	}
	
	 @Bean
	    public JmsListenerContainerFactory<?> connectionFactory(ConnectionFactory connectionFactory,
	                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	        // You could still override some of Boot's default if necessary.
	        return factory;
	    }

	    @Bean // Serialize message content to json using TextMessage
	    public MessageConverter jacksonJmsMessageConverter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        return converter;
	    }
	    
	    @Bean
	    public SubscribableChannel subscribableChannel1() {
	    	return new PublishSubscribeChannel();
	    }
	    @Bean
	    public QueueChannel jmsOutChannel(){
	    return new QueueChannel();
	    }
}
