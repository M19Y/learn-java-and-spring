package com.m19y.learn.repository;

import com.m19y.learn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository // tidak wajib
public interface CategoryRepository extends JpaRepository<Category, Long> {

  // select * from category where name =? limit 1
  Optional<Category> findFirstByName(String name);

  // select * from category where name like ?
  List<Category> findAllByNameLike(String name);
}
