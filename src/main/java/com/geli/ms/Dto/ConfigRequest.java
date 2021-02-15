package com.geli.ms.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfigRequest {
    @JsonProperty("@dto")
    private String action;

    @JsonProperty("site_id")
    private String siteId;

    @JsonProperty("eos_serial")
    private String eosId;

    @JsonProperty("module_id")
    private String moduleId;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("current_weather_interval")
    private int currWeatherInterval;

    @JsonProperty("forecasted_weather_interval")
    private int forecastedWeatherInterval;

    @JsonProperty("first_telemetry_time")
    private long firstTelemetryTime;
}
