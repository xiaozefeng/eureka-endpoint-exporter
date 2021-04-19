package io.github.mickey.eureka.endpoint.exporter.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.mickey.eureka.endpoint.exporter.api.EurekaAPI;
import io.github.mickey.eureka.endpoint.exporter.api.dto.AppDetailResp;
import io.github.mickey.eureka.endpoint.exporter.api.dto.InstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Autowired
    private EurekaAPI eurekaAPI;

    @Bean
    public Cache<String, List<InstanceDTO>> createEurekaServerCache() {
        final LoadingCache<String, List<InstanceDTO>> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, List<InstanceDTO>>() {
                    @Override
                    public List<InstanceDTO> load(String key) throws Exception {
                        final AppDetailResp body = eurekaAPI.getApp(key).execute().body();
                        Assert.notNull(body, "get app detail failed, appid:" + key);
                        return body.getApplication().getInstance();
                    }
                });
        return cache;
    }




}
