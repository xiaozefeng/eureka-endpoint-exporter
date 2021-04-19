package io.github.mickey.eureka.endpoint.exporter.config;

import io.github.mickey.eureka.endpoint.exporter.api.EurekaAPI;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    @Resource
    private EurekaServerProperties eurekaServerProperties;

    @Value("${spring.profiles.active}")
    private String env;

    @Bean
    public Retrofit createRetrofit(@Autowired OkHttpClient okHttpClient) {
        String url = "";
        if ("prod".equals(env)) {
            url = eurekaServerProperties.getProd();
        }else {
            url = eurekaServerProperties.getTest();
        }
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    @Bean
    public OkHttpClient createOKHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public EurekaAPI createEurekaAPI(@Autowired Retrofit retrofit) {
        return retrofit.create(EurekaAPI.class);
    }
}
