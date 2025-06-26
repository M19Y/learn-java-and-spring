package com.m19y.learn.service;

import com.m19y.learn.repository.MemberRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

  @Getter
  @Autowired
  @Qualifier("normalMember")
  private MemberRepository normalRepository;

  @Getter
  @Autowired
  @Qualifier("premiumMember")
  private MemberRepository premiumRepository;
}
