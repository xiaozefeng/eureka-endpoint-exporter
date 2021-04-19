package io.github.mickey.eureka.endpoint.exporter.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@lombok.NoArgsConstructor
@lombok.Data
public class AppListResp {

    @SerializedName("applications")
    private ApplicationsDTO applications;


    @NoArgsConstructor
    @Data
    public static class ApplicationsDTO {

        @SerializedName("application")
        private List<ApplicationDTO> application;
    }

}