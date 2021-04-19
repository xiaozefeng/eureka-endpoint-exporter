package io.github.mickey.eureka.endpoint.exporter.vo;

import lombok.Data;

import java.util.List;

@Data
public class EurekaServiceVO {

    private String name;
    private List<String> ips;
}
