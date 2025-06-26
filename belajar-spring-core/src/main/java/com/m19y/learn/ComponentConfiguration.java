package com.m19y.learn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.m19y.learn.service",
        "com.m19y.learn.repository",
        "com.m19y.learn.scan",
})
public class ComponentConfiguration {
}
