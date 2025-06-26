package com.m19y.learn.scan;

import com.m19y.learn.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MemberConfiguration {

  @Bean
  @Primary
  public MemberRepository normalMember(){
    return new MemberRepository();
  }

  @Bean
  public MemberRepository premiumMember(){
    return new MemberRepository();
  }
}
