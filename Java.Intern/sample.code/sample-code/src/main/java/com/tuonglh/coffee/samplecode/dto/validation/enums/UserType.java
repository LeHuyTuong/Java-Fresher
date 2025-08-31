package com.tuonglh.coffee.samplecode.dto.validation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("user")
    USER,
    @JsonProperty("owner")
    OWNER;
}
