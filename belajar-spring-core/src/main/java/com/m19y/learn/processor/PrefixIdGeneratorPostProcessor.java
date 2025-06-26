package com.m19y.learn.processor;

import com.m19y.learn.aware.IdAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PrefixIdGeneratorPostProcessor implements BeanPostProcessor, Ordered {

  @Override
  public int getOrder() {
    return 2;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    log.info("prefix Id generator processor for bean {}", beanName);
    if(bean instanceof IdAware){
    log.info("prefix set Id generator processor for bean {}", beanName);
      IdAware idAware = (IdAware) bean;
      idAware.setId("Prefix-" + idAware.getId());
    }
    return bean;
  }
}
