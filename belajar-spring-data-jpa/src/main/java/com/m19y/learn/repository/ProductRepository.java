package com.m19y.learn.repository;

import com.m19y.learn.entity.Category;
import com.m19y.learn.entity.Product;
import com.m19y.learn.model.SimpleProduct;
import com.m19y.learn.model.SimpleProductRecord;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  List<Product> findAllByCategory_name(String name);

  // implemented sorting
  List<Product> findAllByCategory_name(String name, Sort sort);

  // implemented pageable (auto include sorting)
  Page<Product> findAllByCategory_name(String name, Pageable pageable);

  Long countByCategory_name(String name);

  // hanya melakukan (select id from products where name = ? limit ?)
  boolean existsByName(String name);

  // harus ditambahkan transactional (default-nya sudah ada tapi read only)
  @Transactional
  int deleteByName(String name);

  // named query
  List<Product> searchProductUsingName(@Param("name") String name);

  List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

  // query method
  @Query(value = "SELECT p FROM Product p where p.name LIKE :name or p.category.name LIKE :name")
  List<Product> searchProduct(@Param("name") String name);

  // query method with pageable
  @Query(value = "SELECT p.name FROM Product p where p.name LIKE :name or p.category.name LIKE :name")
  List<String> searchProduct(@Param("name") String name, Pageable pageable);

  // query method that has return value PageResult
  @Query(value = "SELECT p FROM Product p where p.name LIKE :name or p.category.name LIKE :name",
          countQuery = "SELECT count(p) FROM Product p where p.name LIKE :name or p.category.name LIKE :name")
  Page<Product> searchProductPage(@Param("name") String name, Pageable pageable);

  // modifying
  @Modifying
  @Query(value = "DELETE FROM Product p where p.name = :name")
  int deleteProductUsingName(@Param("name") String name);

  @Modifying
  @Query(value = "UPDATE Product p set p.price = :price where p.name =:name")
  int updateProductPriceUsingName(@Param("price") Long price, @Param("name") String name);

  Stream<Product> streamAllByCategory(Category category);

  // slice
  Slice<Product> findAllByCategory(Category category, Pageable pageable);

  // lociking
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Product> findFirstByIdEquals(Long id);

  // projection
  // using interface
//  List<SimpleProduct> findAllByNameLike(String name);

  // using record
  <T> List<T> findAllByNameLike(String name, Class<T> tClass);

}
