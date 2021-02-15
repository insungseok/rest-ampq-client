package com.geli.ms.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfigResponse {
    @JsonProperty("site_id")
    private String siteId;
}
