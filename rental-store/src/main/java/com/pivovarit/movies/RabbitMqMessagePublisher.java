package com.pivovarit.movies;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

record RabbitMqMessagePublisher(RabbitTemplate rabbitTemplate) implements MessagePublisher {

    @Override
    public void send(MovieRentalEvent event) {
        System.out.println("Sending message to RabbitMQ: " + event.toString());

        rabbitTemplate.convertAndSend("rentals", RentalEvent.class.getName());
    }
}
