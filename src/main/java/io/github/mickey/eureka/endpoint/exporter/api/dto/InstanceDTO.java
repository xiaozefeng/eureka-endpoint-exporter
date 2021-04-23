package io.github.mickey.eureka.endpoint.exporter.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InstanceDTO {
    @SerializedName("instanceId")
    private String instanceId;
    @SerializedName("hostName")
    private String hostName;
    @SerializedName("app")
    private String app;
    @SerializedName("ipAddr")
    private String ipAddr;
    @SerializedName("status")
    private String status;
    // @SerializedName("overriddenStatus")
    // private String overriddenStatus;
    // @SerializedName("countryId")
    // private Integer countryId;
    // @SerializedName("leaseInfo")
    // private LeaseInfoDTO leaseInfo;
    // @SerializedName("homePageUrl")
    // private String homePageUrl;
    // @SerializedName("statusPageUrl")
    // private String statusPageUrl;
    // @SerializedName("healthCheckUrl")
    // private String healthCheckUrl;
    // @SerializedName("vipAddress")
    // private String vipAddress;
    // @SerializedName("secureVipAddress")
    // private String secureVipAddress;
    // @SerializedName("isCoordinatingDiscoveryServer")
    // private String isCoordinatingDiscoveryServer;
    // @SerializedName("lastUpdatedTimestamp")
    // private String lastUpdatedTimestamp;
    // @SerializedName("lastDirtyTimestamp")
    // private String lastDirtyTimestamp;
    // @SerializedName("actionType")
    // private String actionType;


    // @NoArgsConstructor
    // @Data
    // public static class LeaseInfoDTO {
    //     @SerializedName("renewalIntervalInSecs")
    //     private Integer renewalIntervalInSecs;
    //     @SerializedName("durationInSecs")
    //     private Integer durationInSecs;
    //     @SerializedName("registrationTimestamp")
    //     private Long registrationTimestamp;
    //     @SerializedName("lastRenewalTimestamp")
    //     private Long lastRenewalTimestamp;
    //     @SerializedName("evictionTimestamp")
    //     private Integer evictionTimestamp;
    //     @SerializedName("serviceUpTimestamp")
    //     private Long serviceUpTimestamp;
    // }

}