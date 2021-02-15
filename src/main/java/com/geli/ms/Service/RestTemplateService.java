package com.geli.ms.Service;

import com.geli.ms.Dto.ConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;


@Service
public class RestTemplateService {

    private RestTemplateAbstraction<String> restTemplateAbstraction;

    @Value("${geli.weather.endpoints}")
    private String endpoints;

    @Value("${geli.weather.moduleid}")
    private String moduleid;

    @Autowired
    public RestTemplateService(RestTemplateAbstraction<String> restTemplateAbstraction) {
        this.restTemplateAbstraction = restTemplateAbstraction;
    }

    public String registerWeatherConfig(final String siteId, final String eosId) {

        ConfigRequest configRequest = new ConfigRequest();
        configRequest.setAction("WeatherRegistration");
        configRequest.setSiteId(siteId);
        configRequest.setEosId(eosId);
        configRequest.setModuleId(moduleid);
        configRequest.setLatitude(40.71);
        configRequest.setLongitude(74.00);
        configRequest.setCurrWeatherInterval(1);
        configRequest.setForecastedWeatherInterval(5);
        configRequest.setFirstTelemetryTime(ZonedDateTime.now().toInstant().toEpochMilli());

        return restTemplateAbstraction.post(endpoints + "/api/v1/register", HttpHeaders.EMPTY, configRequest, String.class).getBody();
    }

    public String deRegisterWeatherConfig(final String siteId, final String eosId) {

        ConfigRequest configRequest = new ConfigRequest();
        configRequest.setAction("WeatherDeRegistration");
        configRequest.setSiteId(siteId);
        configRequest.setEosId(eosId);
        configRequest.setModuleId(moduleid);

        return restTemplateAbstraction.post(endpoints + "/api/v1/unregister", HttpHeaders.EMPTY, configRequest, String.class).getBody();
    }

    public String getCurrentWeatherData(final String siteId) {
        return restTemplateAbstraction.get(endpoints + "/api/v1/sites/" + siteId + "/currentweatherdata", HttpHeaders.EMPTY, String.class).getBody();
    }

    public String getForecastedWeatherData(final String siteId) {
        return restTemplateAbstraction.get(endpoints + "/api/v1/sites/" + siteId + "/forecastedweatherdata", HttpHeaders.EMPTY, String.class).getBody();
    }
}
