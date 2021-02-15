package com.geli.ms.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RabbitMQCurrentWeather {
    @JsonProperty("@dto")
    private String action;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("apparentTemperature")
    private float apparentTemperature;

    @JsonProperty("humidity")
    private float humidity;

    @JsonProperty("cloudcover")
    private float cloudcover;

    @JsonProperty("windspeed")
    private float windspeed;

    @JsonProperty("dni")
    private float dni;
}
