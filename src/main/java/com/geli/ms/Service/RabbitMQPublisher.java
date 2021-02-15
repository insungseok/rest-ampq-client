package com.geli.ms.Service;

import com.geli.ms.Dto.ConfigRequest;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topic;

    @Value("${amqp.routingkey.pub.current}")
    private String currentRoutingKey;

    @Value("${amqp.routingkey.pub.forecasted}")
    private String forecastedRoutingKey;

    @Value("${geli.weather.moduleid}")
    private String moduleid;

    public String getCurrentWeather(final String siteId, final String eosId){
        ConfigRequest request = new ConfigRequest();
        request.setAction("CurrentWeatherDataRequest");
        request.setSiteId(siteId);
        request.setEosId(eosId);
        request.setModuleId(moduleid);

        rabbitTemplate.convertAndSend(topic.getName(), currentRoutingKey, request);

        return "Publish get current weather data success";
    }

    public String getForecastedWeather(final String siteId, final String eosId){
        ConfigRequest request = new ConfigRequest();
        request.setAction("ForecastedWeatherDataRequest");
        request.setSiteId(siteId);
        request.setEosId(eosId);
        request.setModuleId(moduleid);

        rabbitTemplate.convertAndSend(topic.getName(), forecastedRoutingKey, request);

        return "Publish get forecasted weather data success";
    }
}
