package com.tuonglh.coffee.samplecode.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {
    @JsonProperty("active")
    Active,
    @JsonProperty("inactive")
    Inactive,
    @JsonProperty("none")
    NONE
}
