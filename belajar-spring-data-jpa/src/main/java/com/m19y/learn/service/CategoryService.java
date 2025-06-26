package com.m19y.learn.service;

import com.m19y.learn.entity.Category;
import com.m19y.learn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  // programmatic transaction
  @Autowired
  private TransactionOperations transactionOperations;

  // manual transaction
  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  @Transactional
  public void create(){
    for (int i = 0; i < 5; i++) {
      Category category = new Category();
      category.setName("category - " + i);
      categoryRepository.save(category);
    }

    throw new RuntimeException("ups rollback please!");
  }

  // aop tidak akan berjalan!
  // walaupun ada transational
  // AOP akan berjalan jika dipanggil di luar class, bukan di class yang sama
  public void test(){
    create();
  }

  public void error(){
    throw new RuntimeException("error");
  }

  // manual transactional (akan jalan walaupun di panggil di method yang memiliki kelass yang sama)
  // karena tidak menggunakan AOP seperti pada @Transactional
  public void createCategoriesTransactional(){
    transactionOperations.executeWithoutResult(transactionStatus -> {

      for (int i = 0; i < 5; i++) {
        Category category = new Category();
        category.setName("category - " + i);
        categoryRepository.save(category);
      }

      error();
    });

  }

  // akan aktif karena tidak menggunakan AOP
  public void test2(){
    createCategoriesTransactional();
  }

  public void createCategoriesManualTransactional(){
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setTimeout(10);
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus transaction = platformTransactionManager.getTransaction(definition);
    try{
      for (int i = 0; i < 5; i++) {
        Category category = new Category();
        category.setName("category - " + i);
        categoryRepository.save(category);
      }

      error();
      platformTransactionManager.commit(transaction);
    }catch (Throwable t){
      platformTransactionManager.rollback(transaction);
      throw t;
    }
  }

  public void test3(){
    createCategoriesManualTransactional();
  }
}
