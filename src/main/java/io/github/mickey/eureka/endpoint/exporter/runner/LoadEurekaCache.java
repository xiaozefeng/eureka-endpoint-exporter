package io.github.mickey.eureka.endpoint.exporter.runner;

import com.google.common.cache.Cache;
import io.github.mickey.eureka.endpoint.exporter.api.EurekaAPI;
import io.github.mickey.eureka.endpoint.exporter.api.dto.AppListResp;
import io.github.mickey.eureka.endpoint.exporter.api.dto.ApplicationDTO;
import io.github.mickey.eureka.endpoint.exporter.api.dto.InstanceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
@Slf4j
public class LoadEurekaCache implements ApplicationRunner {

    @Autowired
    private EurekaAPI eurekaAPI;

    @Autowired
    private Cache<String, List<InstanceDTO>> cache;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final AppListResp resp = eurekaAPI.getApps().execute().body();
        Assert.notNull(resp, "get eureka apps failed.");
        for (ApplicationDTO dto : resp.getApplications().getApplication()) {
            cache.put(dto.getName(), dto.getInstance());
        }
        log.info("load eureka server apps successfully.");
    }
}
