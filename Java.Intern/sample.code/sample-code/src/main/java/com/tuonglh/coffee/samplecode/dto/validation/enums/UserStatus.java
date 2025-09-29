package com.tuonglh.coffee.samplecode.dto.validation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum aUserStatus {
    @JsonProperty("active")
    ACTIVE,
    @JsonProperty("inactive")
    INACTIVE,
    @JsonProperty("none")
    NONE;
}
