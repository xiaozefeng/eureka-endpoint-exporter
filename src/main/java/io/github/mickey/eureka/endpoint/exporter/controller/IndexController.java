package io.github.mickey.eureka.endpoint.exporter.controller;

import com.google.common.cache.Cache;
import io.github.mickey.eureka.endpoint.exporter.api.EurekaAPI;
import io.github.mickey.eureka.endpoint.exporter.api.dto.InstanceDTO;
import io.github.mickey.eureka.endpoint.exporter.vo.EurekaServiceVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class IndexController {


    public IndexController(EurekaAPI eurekaAPI) {
        this.eurekaAPI = eurekaAPI;
    }

    private final EurekaAPI eurekaAPI;


    @GetMapping("apps")
    public Object getApps() throws IOException {
        return eurekaAPI.getApps().execute().body();
    }

    @Autowired
    private Cache<String, List<InstanceDTO>> cache;

    @SneakyThrows
    @GetMapping("{appid}")
    public Object getApp(@PathVariable String appid) {
        // cache for all name
        List<String> names = new ArrayList<>();
        for (Map.Entry<String, List<InstanceDTO>> entry : cache.asMap().entrySet()) {
            names.add(entry.getKey());
        }

        List<String> searchResult = new ArrayList<>();
        // fuzzy search appid
        for (String name : names) {
            if (name.toLowerCase().contains(appid)) {
                searchResult.add(name);
            }
        }

        List<EurekaServiceVO> result = new ArrayList<>();
        // return all find result
        for (String possibleAppId : searchResult) {
            final List<InstanceDTO> instances = cache.get(possibleAppId, ()-> Objects.requireNonNull(eurekaAPI.getApp(possibleAppId).execute().body()).getApplication().getInstance());
            EurekaServiceVO vo = convertToVO(possibleAppId, instances);
            result.add(vo);
        }
        return result;
    }

    private EurekaServiceVO convertToVO(String appName, List<InstanceDTO> instances) {
        final EurekaServiceVO vo = new EurekaServiceVO();
        vo.setName(appName);
        final List<String> ips = instances.stream().map(InstanceDTO::getIpAddr).collect(Collectors.toList());
        vo.setIps(ips);
        return vo;
    }


}
