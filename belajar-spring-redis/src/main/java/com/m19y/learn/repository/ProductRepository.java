package com.m19y.learn.repository;

import com.m19y.learn.keyspace.Product;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends KeyValueRepository<Product, String> {
}
