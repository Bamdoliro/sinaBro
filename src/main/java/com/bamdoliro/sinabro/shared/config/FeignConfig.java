package com.bamdoliro.sinabro.shared.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.bamdoliro.sinabro.infrastructure")
@Configuration
public class FeignConfig {
}
