package com.m19y.learn;

import com.m19y.learn.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class BeanFactoryTest {

  @Test
  void testBeanFactory() {

    ApplicationContext context = new AnnotationConfigApplicationContext(ObjectProviderConfiguration.class);

    ObjectProvider<Foo> fooObjectProvider = context.getBeanProvider(Foo.class);
    List<Foo> fooList = fooObjectProvider.stream().collect(Collectors.toList());
    System.out.println(fooList);

    Map<String, Foo> beans = context.getBeansOfType(Foo.class);
    System.out.println(beans);
  }
}
