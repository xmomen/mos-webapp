package com.xmomen.mos.module.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Jeng on 2016/1/4.
 */
@Configuration
@ImportResource(value = {"classpath:config/spring-config-cache.xml","classpath:config/spring-config-shiro.xml"})
public class Oauth2Config {

}
