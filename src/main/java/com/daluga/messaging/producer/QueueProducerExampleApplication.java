package com.daluga.messaging.producer;

import com.daluga.messaging.producer.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;

@SpringBootApplication
@EnableJms
public class QueueProducerExampleApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueProducerExampleApplication.class);

    @Autowired
    private Producer producer;

    @Value("${queue.name}")
    private String queueName;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(QueueProducerExampleApplication.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        SpringApplication.run(QueueProducerExampleApplication.class, args);
    }

    @Bean
    public Queue queue() {
        //return new ActiveMQQueue(queueName);
        return new ActiveMQQueue("baseball.request");
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.debug("Spring Boot messaging producer example  has started....");
        LOGGER.debug("Queue name: " + queueName);
        producer.send("I put a message to the queue....");
        LOGGER.debug("Spring Boot messaging producer example has ended....");

    }
}
