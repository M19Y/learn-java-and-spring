package com.m19y.learn;

import com.m19y.learn.service.MerchantServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {MerchantServiceImpl.class})
public class InheritanceConfiguration {
}
