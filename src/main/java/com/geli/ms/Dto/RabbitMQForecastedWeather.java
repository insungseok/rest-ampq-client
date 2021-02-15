package com.geli.ms.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RabbitMQForecastedWeather {
    @JsonProperty("@dto")
    private String action;

    @JsonProperty("timestamp")
    private List<Long> timestamp;

    @JsonProperty("apparentTemperature")
    private List<Float> apparentTemperature;

    @JsonProperty("humidity")
    private List<Float> humidity;

    @JsonProperty("cloudcover")
    private List<Float> cloudcover;

    @JsonProperty("windspeed")
    private List<Float> windspeed;

    @JsonProperty("dni")
    private List<Float> dni;
}
