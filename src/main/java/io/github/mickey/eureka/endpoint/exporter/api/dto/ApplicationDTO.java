package io.github.mickey.eureka.endpoint.exporter.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ApplicationDTO {
    @SerializedName("name")
    private String name;
    @SerializedName("instance")
    private List<InstanceDTO> instance;

}