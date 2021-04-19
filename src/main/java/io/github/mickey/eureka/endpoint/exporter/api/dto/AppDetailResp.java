package io.github.mickey.eureka.endpoint.exporter.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AppDetailResp {


    @SerializedName("application")
    private ApplicationDTO application;

}
