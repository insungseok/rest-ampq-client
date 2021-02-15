package com.geli.ms.Service;

import com.geli.ms.Dto.RabbitMQCurrentWeather;
import com.geli.ms.Dto.RabbitMQForecastedWeather;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQSubscriber {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "${amqp.exchange}", type = "topic", durable = "true"), key = "${amqp.routingkey.sub.current}"))
    public void getAllEOSCurrentWeather(final RabbitMQCurrentWeather rabbitMQCurrentWeather) {
        log.info("Current Weather Data: {}", rabbitMQCurrentWeather.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "${amqp.exchange}", type = "topic", durable = "true"), key = "${amqp.routingkey.sub.forecasted}"))
    public void getAllEOSForecastedWeather(final RabbitMQForecastedWeather rabbitMQForecastedWeather) {
        log.info("Forecasted Weather Data: {}", rabbitMQForecastedWeather.toString());
    }
}
