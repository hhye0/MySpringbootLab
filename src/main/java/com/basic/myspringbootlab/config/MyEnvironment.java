package com.basic.myspringbootlab.config;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class MyEnvironment {
    private String mode;
}
