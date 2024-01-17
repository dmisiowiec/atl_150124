package com.pivovarit.movies;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

class RentalHistoryMessageRelay {
    private final RentalHistory history;
    private final MessagePublisher rabbitTemplate;

    RentalHistoryMessageRelay(RentalHistory history, MessagePublisher messagePublisher) {
        this.history = history;
        this.rabbitTemplate = messagePublisher;
    }

    @Scheduled(fixedDelay = 1000)
    public void process() {

        history.unprocessed()
          .forEach(event -> {
              System.out.println("sending to rmq: " + event.toString());
              rabbitTemplate.send(event);
              history.markProcessed(event);
          });
    }
}
