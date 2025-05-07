package com.basic.Mmyspringbootlab.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myprop")
@Getter @Setter
public class MyPropProperties {
    private String Username;
    private int port;
}
