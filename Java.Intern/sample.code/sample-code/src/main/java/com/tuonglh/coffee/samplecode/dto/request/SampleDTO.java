package com.tuonglh.coffee.samplecode.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import java.io.Serializable;

// @Data  = @GEtter + Setter + equalsandHashcode
@Getter
@Builder
@AllArgsConstructor
public class SampleDTO implements Serializable {
    private Integer id;
    private final String name;
}
