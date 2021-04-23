package io.github.mickey.eureka.endpoint.exporter.controller;

import io.github.mickey.eureka.endpoint.exporter.api.EurekaAPI;
import io.github.mickey.eureka.endpoint.exporter.api.dto.AppListResp;
import io.github.mickey.eureka.endpoint.exporter.api.dto.ApplicationDTO;
import io.github.mickey.eureka.endpoint.exporter.api.dto.InstanceDTO;
import io.github.mickey.eureka.endpoint.exporter.vo.EurekaServiceVO;
import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class IndexController {


    public IndexController(EurekaAPI eurekaAPI) {
        this.eurekaAPI = eurekaAPI;
    }

    private final EurekaAPI eurekaAPI;


    // @GetMapping("apps")
    // public Object getApps() throws IOException {
    //     return eurekaAPI.getApps().execute().body();
    // }

    // @Autowired
    // private Cache<String, List<InstanceDTO>> cache;

    @SneakyThrows
    @GetMapping("search/{appid}")
    public Object getApp(@PathVariable String appid) {
        // // cache for all name
        // List<String> names = new ArrayList<>();
        // for (Map.Entry<String, List<InstanceDTO>> entry : cache.asMap().entrySet()) {
        //     names.add(entry.getKey());
        // }
        //
        // List<String> searchResult = new ArrayList<>();
        // // fuzzy search appid
        // for (String name : names) {
        //     if (name.toLowerCase().contains(appid)) {
        //         searchResult.add(name);
        //     }
        // }
        //
        // List<EurekaServiceVO> result = new ArrayList<>();
        // // return all find result
        // for (String possibleAppId : searchResult) {
        //     final List<InstanceDTO> instances = cache.get(possibleAppId, ()-> Objects.requireNonNull(eurekaAPI.getApp(possibleAppId).execute().body()).getApplication().getInstance());
        //     EurekaServiceVO vo = convertToVO(possibleAppId, instances);
        //     result.add(vo);
        // }
        // return result;

        final AppListResp resp = eurekaAPI.getApps().execute().body();
        Assert.notNull(resp, "获取所有apps失败");

        final List<ApplicationDTO> apps = resp.getApplications().getApplication();
        final List<ApplicationDTO> filteredName = apps.stream().filter(e -> e.getName().toLowerCase().contains(appid))
                .collect(Collectors.toList());
        return filteredName.stream().map(e -> convertToVO(e.getName(), e.getInstance()));
    }

    private EurekaServiceVO convertToVO(String appName, List<InstanceDTO> instances) {
        final EurekaServiceVO vo = new EurekaServiceVO();
        vo.setName(appName);
        final List<String> ips = instances.stream().map(InstanceDTO::getIpAddr).collect(Collectors.toList());
        vo.setIps(ips);
        return vo;
    }


}
