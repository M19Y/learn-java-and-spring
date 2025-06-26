package com.m19y.learn.service;

import com.m19y.learn.repository.CustomerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

  @Getter
  @Autowired
  private CustomerRepository customerRepository;
}
