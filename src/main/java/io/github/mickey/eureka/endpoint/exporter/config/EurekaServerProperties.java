package io.github.mickey.eureka.endpoint.exporter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "eureka.server")
@Component
@Data
public class EurekaServerProperties {

    private String prod;

    private String test;

}
