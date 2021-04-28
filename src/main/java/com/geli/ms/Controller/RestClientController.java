package com.geli.ms.Controller;

import com.geli.ms.Dto.RabbitMQForecastedWeather;
import com.geli.ms.Service.RabbitMQPublisher;
import com.geli.ms.Service.RestTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestClientController {

    private RestTemplateService restTemplateService;

    private RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    public RestClientController(RestTemplateService restTemplateService, RabbitMQPublisher rabbitMQPublisher) {
        this.restTemplateService = restTemplateService;
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @GetMapping("/config/registration")
    public ResponseEntity<String> registerWeatherConfig(@RequestParam("siteId") final String siteId, @RequestParam("eosId") final String eosId, @RequestParam("cItv") final int cItv, @RequestParam("fItv") final int fItv) {
        return ResponseEntity.ok(restTemplateService.registerWeatherConfig(siteId, eosId, cItv, fItv));
    }

    @GetMapping("/config/deregistration")
    public ResponseEntity<String> deRegisterWeatherConfig(@RequestParam("siteId") final String siteId, @RequestParam("eosId") final String eosId) {
        return ResponseEntity.ok(restTemplateService.deRegisterWeatherConfig(siteId, eosId));
    }

    @GetMapping("/data/currentweather")
    public ResponseEntity<String> getCurrentWeatherData(@RequestParam("protocol") final String protocol, @RequestParam("siteId") final String siteId, @RequestParam("eosId") final String eosId) {
        if (StringUtils.equals(protocol, "rest")) {
            return ResponseEntity.ok(restTemplateService.getCurrentWeatherData(siteId));
        } else if (StringUtils.equals(protocol, "amqp")) {
            return ResponseEntity.ok(rabbitMQPublisher.getCurrentWeather(siteId, eosId));
        } else {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/data/forecastedweather")
    public ResponseEntity<String> getForecastedWeatherData(@RequestParam("protocol") final String protocol, @RequestParam("siteId") final String siteId, @RequestParam("eosId") final String eosId) {
        if (StringUtils.equals(protocol, "rest")) {
            return ResponseEntity.ok(restTemplateService.getForecastedWeatherData(siteId));
        } else if (StringUtils.equals(protocol, "amqp")) {
            return ResponseEntity.ok(rabbitMQPublisher.getForecastedWeather(siteId, eosId));
        } else {
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }
}
