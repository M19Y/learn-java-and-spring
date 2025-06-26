package com.m19y.learn.processor;

import com.m19y.learn.data.Foo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class FooBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  // teknik pembuatan bean seperti biasanya diggunakan saat membuat framework di atas spring framework
  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    GenericBeanDefinition definition = new GenericBeanDefinition();
    definition.setBeanClass(Foo.class);
    definition.setScope("singleton");
    registry.registerBeanDefinition("foo", definition);
  }

  // method di atas sama halnya kita membuat bean baru tanpa melalui annotasi configuration dan bean

  // contoh menggunakan configuration dan bean:
  // @Configuration
  // class SimpleConfiguration{
  //    @Bean
  //    public Foo foo(){
  //      return new Foo();
  //    }
  // }

}
