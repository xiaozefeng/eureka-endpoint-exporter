package io.github.mickey.eureka.endpoint.exporter.api;

import io.github.mickey.eureka.endpoint.exporter.api.dto.AppDetailResp;
import io.github.mickey.eureka.endpoint.exporter.api.dto.AppListResp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface EurekaAPI {


    @GET("eureka/apps")
    @Headers("Accept: application/json")
    Call<AppListResp> getApps();

    /**
     * @param appid instance id  or  name
     * @return
     */
    @GET("eureka/apps/{appid}")
    @Headers("Accept: application/json")
    Call<AppDetailResp> getApp(@Path("appid") String appid);
}
