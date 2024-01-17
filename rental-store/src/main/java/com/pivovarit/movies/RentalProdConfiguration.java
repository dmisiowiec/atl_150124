package com.pivovarit.movies;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
class RentalProdConfiguration {

    @Bean
    Jdbi jdbi(DataSource ds) {
        Jdbi jdbi = Jdbi.create(new TransactionAwareDataSourceProxy(ds));
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    MovieRepository jdbiMovieRepository(Jdbi jdbi) {
        return new JdbiMovieRepository(jdbi);
    }

    @Bean
    DescriptionsRepository descriptionsRepositoryAdapter(@Value("${service.descriptions.url}") String url) {
        return new RestMovieDescriptionsRepository(url);
    }

    @Bean
    RentalHistory jdbiRentalHistory(Jdbi jdbi) {
        return new JdbiRentalHistory(jdbi);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory cf, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cf);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue queue() {
        return new Queue("rentals", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("rentals");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RentalEvent.class.getPackageName() + ".#");
    }

    @Bean
    MessagePublisher rmqMessagePublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqMessagePublisher(rabbitTemplate);
    }
}
