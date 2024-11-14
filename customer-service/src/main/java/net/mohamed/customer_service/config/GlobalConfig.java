package net.mohamed.customer_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "global.params")
@RefreshScope

// Mais ca ne va pas marcher car les record sont immmuable cad une fois q'ils sont initialiser il ne peuvent pas
// etre modifier !!!!!!!!!!!!!!!!!
public record GlobalConfig(int p1,int p2) {

}
