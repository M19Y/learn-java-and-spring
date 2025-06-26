package com.m19y.learn;

import com.m19y.learn.repository.CategoryRepository;
import com.m19y.learn.repository.CustomerRepository;
import com.m19y.learn.repository.MemberRepository;
import com.m19y.learn.repository.ProductRepository;
import com.m19y.learn.service.CategoryService;
import com.m19y.learn.service.CustomerService;
import com.m19y.learn.service.MemberService;
import com.m19y.learn.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentDependencyInjectionTest {

  private ApplicationContext context;
  @BeforeEach
  void setUp() {
    context = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
  }

  @Test
  void testConstructorDI() {

    ProductService productService = context.getBean(ProductService.class);

    Assertions.assertNotNull(productService.getProductRepository());

    ProductRepository productRepository = context.getBean(ProductRepository.class);

    Assertions.assertSame(productRepository, productService.getProductRepository());
  }

  @Test
  void testSetterDI() {
    CategoryService categoryService = context.getBean(CategoryService.class);
    CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);

    Assertions.assertSame(categoryService.getCategoryRepository(), categoryRepository);
  }

  @Test
  void testFiledDI() {
    CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
    CustomerService customerService = context.getBean(CustomerService.class);

    Assertions.assertSame(customerService.getCustomerRepository(), customerRepository);
  }

  @Test
  void testFiledDIWithQualifier() {
    MemberRepository premiumMember = context.getBean("premiumMember", MemberRepository.class);
    MemberRepository normalMember = context.getBean("normalMember", MemberRepository.class);
    MemberService memberService = context.getBean(MemberService.class);

    Assertions.assertSame(premiumMember, memberService.getPremiumRepository());
    Assertions.assertSame(normalMember, memberService.getNormalRepository());
  }
}
