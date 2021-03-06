package com.hp.mobile.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Spieler Orasio
 * on 07-Apr-16.
 */
@Component
public class MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private Queue queue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void createQueue() {
        this.amqpAdmin.declareQueue(queue);
    }

    //    @Scheduled(fixedDelay = 1000L)
    public void send(String message) {
        logger.info("sending message : {}", message);

        this.rabbitTemplate.convertAndSend("someQueue", message);
    }
}
