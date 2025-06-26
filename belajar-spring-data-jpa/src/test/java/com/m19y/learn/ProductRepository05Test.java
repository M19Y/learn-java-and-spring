package com.m19y.learn;

import com.m19y.learn.entity.Category;
import com.m19y.learn.entity.Product;
import com.m19y.learn.model.SimpleProduct;
import com.m19y.learn.model.SimpleProductRecord;
import com.m19y.learn.repository.CategoryRepository;
import com.m19y.learn.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionOperations;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductRepository05Test {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private TransactionOperations transactionOperations;

  @BeforeEach
  void setUp() {
    productRepository.deleteAll();
    categoryRepository.deleteAll();
    Category category1 = new Category();
    category1.setName("Family");

    Category category2 = new Category();
    category2.setName("Neighbour");

    Category category3 = new Category();
    category3.setName("Village");

    Category category4 = new Category();
    category4.setName("Party");

    Category category5 = new Category();
    category5.setName("Hometown");

    Category category6 = new Category();
    category6.setName("Hometown 2");

    categoryRepository.saveAll(
            List.of(category1,
                    category2,
                    category3,
                    category4,
                    category5,
                    category6
            )
    );

    Product product1 = new Product();
    product1.setCategory(category4);
    product1.setPrice(10L);
    product1.setName("Disco Lamp");

    Product product2 = new Product();
    product2.setCategory(category1);
    product2.setPrice(3L);
    product2.setName("Spoon");

    Product product3 = new Product();
    product3.setCategory(category1);
    product3.setPrice(2L);
    product3.setName("Plate");

    productRepository.saveAll(List.of(product1, product2, product3));
  }

  @AfterEach
  void tearDown() {
    productRepository.deleteAll();
    categoryRepository.deleteAll();
  }

  @Nested
  class QueryRelationTest{
    @Test
    void shouldReturnListOfProductThatHaveCategoryNameFamily_whenFindAllByCategoryNameFamily() {

      List<Product> family = productRepository.findAllByCategory_name("Family");
      assertFalse(family.isEmpty());

      assertEquals(2, family.size());

      List<String> productNames = family.stream().map(Product::getName).toList();
      assertThat(productNames, containsInAnyOrder("Spoon", "Plate"));
    }
  }

  @Nested
  class SortingTest{
    @Test
    void shouldReturnListOfProductThatHaveCategoryNameFamilyWithAscOrdersById_whenFindAllByCategoryNameFamilyOrderAsc() {

      Sort sortById = Sort.by(Sort.Order.asc("id"));
      List<Product> products = productRepository.findAllByCategory_name("Family", sortById);
      assertNotNull(products);

      assertEquals("Spoon", products.get(0).getName());
      assertEquals("Plate", products.get(1).getName());
    }
  }

  @Nested
  class PageableTest{
    @Test
    void shouldReturnDescOrderWithPageable() {
      // page 1

      // limit 0, 1
      Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));

      Page<Product> family = productRepository.findAllByCategory_name("Family", pageable);
      assertNotNull(family);
      assertNotNull(family.getContent());
      assertEquals(1, family.getContent().size()); // total items in one page
      assertEquals(0, family.getNumber()); // current page
      assertEquals(2, family.getTotalPages());
      assertEquals(2, family.getTotalElements()); // total product secara keseluruhan
      assertEquals("Plate", family.getContent().get(0).getName());

      Pageable pageable1 = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));

      Page<Product> family1 = productRepository.findAllByCategory_name("Family", pageable1);
      assertNotNull(family1);
      assertEquals(1, family1.getContent().size());
      assertEquals(1, family1.getNumber());
      assertEquals(2, family1.getTotalPages());
      assertEquals(2, family1.getTotalElements());
      assertEquals("Spoon", family1.getContent().get(0).getName());
    }
  }

  @Nested
  class CountTest{
    @Test
    void shouldReturn2TotalCategory_whenCountByNameFamily() {

      // by default, jpa already provide count, but this is for all total element
      Long totalElements = productRepository.count();
      assertEquals(3, totalElements);

      // custom (using count by name)
      Long family = productRepository.countByCategory_name("Family");
      assertEquals(2, family);

    }
  }

  @Nested
  class Exists{
    @Test
    void shouldReturnTrue_whenExistsByNameSpoon() {
      boolean isExists = productRepository.existsByName("Spoon");
      assertTrue(isExists);

      isExists = productRepository.existsByName("Motor cycle");
      assertFalse(isExists);
    }

    @Test
    @Disabled
    void shouldThrowError_whenDeleteByNameWithoutTransaction() {
      assertThrows(InvalidDataAccessApiUsageException.class, () -> {
        productRepository.deleteByName("Spoon");
      });
    }

    @Test
    @Transactional
    void shouldReturnOne_whenDeleteByNamePlate() {
      // when we try to delete some entity, we need to provide @Transactional annotation,
      // inside the interface (productRepository) or our method
      // or you can just manually do transactional using
      // transactionOperations or PlatformTransactionalManager
      int recordAffected = productRepository.deleteByName("Plate");

      assertEquals(1, recordAffected);

      recordAffected = productRepository.deleteByName("Car");

      assertEquals(0, recordAffected);

    }



    @Test
    void shouldReturnOne_whenDeleteByNameWithManualTransaction() {

      // semua ini dilakukan dalam satu transactional
      transactionOperations.executeWithoutResult(action -> {
        Category category = categoryRepository.findFirstByName("Family").orElse(null);
        assertNotNull(category);

        Product product = new Product();
        product.setName("Bro");
        product.setCategory(category);
        product.setPrice(10L);

        productRepository.save(product);

        int recordAffected = productRepository.deleteByName("Bro");
        assertEquals(1, recordAffected);

        boolean isBroExists = productRepository.existsByName("Bro");
        assertFalse(isBroExists);
      });
    }

    @Test
    void shouldReturnOne_whenDeleteByNameWithManualTransactionNew() {
      // masing masing memiliki transactional tersediri
      // kekurangan adalah jika transactional-nya berhasil
      // dan ada juga transactional yang gagal
      // maka transactional tidak akan dirollback secara keseluruhan
      // maka nanti di database ada data yang tersave dan ada data yang akan terhapus
      Category category = categoryRepository.findFirstByName("Family").orElse(null); // transactional 1
      assertNotNull(category);

      Product product = new Product();
      product.setName("Bro");
      product.setCategory(category);
      product.setPrice(10L);

      productRepository.save(product); // transactional 2

      int recordAffected = productRepository.deleteByName("Bro"); // transactional 3
      assertEquals(1, recordAffected);

      boolean isBroExists = productRepository.existsByName("Bro"); // transactional 4
      assertFalse(isBroExists);
    }
  }

  @Nested
  class NamedQueryTest{

    @Test
    void shouldReturnOneProduct_whenSearchProductUsingNameDiscoLamp() {

      List<Product> products = productRepository.searchProductUsingName("Disco Lamp");

      assertEquals(1, products.size());
      assertEquals("Disco Lamp", products.get(0).getName());

    }

    @Test
    void shouldReturnOneProduct_whenSearchProductUsingNameWithPageableDiscoLamp() {

      Pageable pageable = PageRequest.of(0, 1);
      List<Product> products = productRepository.searchProductUsingName("Disco Lamp", pageable);

      assertEquals(1, products.size());
      assertEquals("Disco Lamp", products.get(0).getName());

    }
  }

  @Nested
  class QueryMethodTest{

    @Test
    void shouldReturnTwoProductThatHasNameLike_whenSearchProductUsingName() {

      List<Product> products = productRepository.searchProduct("%o%");

      assertFalse(products.isEmpty());

      List<String> productNames = products.stream().map(Product::getName).toList();
      assertThat(productNames, containsInAnyOrder("Disco Lamp", "Spoon"));
    }

    @Test
    void shouldReturnThreeProductWithSortDescId_whenSearchProductUsingNameWithPageable() {

      Sort sort = Sort.by(Sort.Order.desc("id"));
      Pageable pageable = PageRequest.of(0, 3, sort);
      List<String> products = productRepository.searchProduct("%p%", pageable);

      // disco lamp spoon, plate
      assertFalse(products.isEmpty());

      assertEquals(3, products.size());

      assertEquals("Plate", products.get(0));
      assertEquals("Spoon", products.get(1));
      assertEquals("Disco Lamp", products.get(2));

    }

    @Test
    void shouldReturnProductWithPaging_whenSearchProductPage() {
      Sort sort = Sort.by(Sort.Order.asc("id"));
      Pageable pageable = PageRequest.of(0, 3, sort);
      Page<Product> products = productRepository.searchProductPage("%l%",pageable);

      assertEquals(0, products.getNumber());
      assertEquals(1, products.getTotalPages());
      assertEquals(3, products.getTotalElements());
      assertEquals(3, products.getContent().size());

      assertEquals("Plate", products.getContent().get(2).getName());
      assertEquals("Spoon", products.getContent().get(1).getName());
      assertEquals("Disco Lamp", products.getContent().get(0).getName());
    }

    @Test
    void shouldReturnOneRecordAffected_whenDeleteProductUsingName() {

      transactionOperations.executeWithoutResult(action -> {
        int recordAffected = productRepository.deleteProductUsingName("Spoon");
        assertEquals(1, recordAffected);

        assertFalse(productRepository.existsByName("Spoon"));
      });
    }

    @Test
    void shouldUpdatePrice_whenUpdateProductPriceUsingName() {

      transactionOperations.executeWithoutResult(action -> {

        int recordAffected = productRepository.updateProductPriceUsingName(10_000L, "Spoon");

        assertEquals(1, recordAffected);

        List<Product> products = productRepository.searchProduct("Spoon");

        Product product = products.get(0);

        assertEquals("Spoon", product.getName());
        assertEquals(10_000L, product.getPrice());
      });
    }
  }

  @Nested
  class StreamTest{
    @Test
    void streamWithTransactionTest() {
      transactionOperations.executeWithoutResult(action -> {
        Category category = categoryRepository.findFirstByName("Family").orElse(null);
        assertNotNull(category);
        Stream<Product> productStream = productRepository.streamAllByCategory(category);
        productStream.forEach(product -> System.out.println(product.getName()));
      });
    }

    @Test
    void streamWithoutTransactionTest() {

      Category category = categoryRepository.findFirstByName("Family").orElse(null);
      assertNotNull(category);

      assertThrows(InvalidDataAccessApiUsageException.class, () -> {
        Stream<Product> productStream = productRepository.streamAllByCategory(category);
        productStream.forEach(product -> System.out.println(product.getName()));
      });
    }
  }

  @Nested
  class SliceTest{

    @Test
    void testSlice() {
      Category category = categoryRepository.findFirstByName("Family").orElse(null);
      assertNotNull(category);

      Sort sort = Sort.by(Sort.Order.desc("id"));
      Pageable pageable = PageRequest.of(0, 1, sort);

      Slice<Product> products = productRepository.findAllByCategory(category, pageable);

      int temp = 0;
      while(products.hasNext()){
        temp++;
        products = productRepository.findAllByCategory(category, products.nextPageable());
      }
      assertEquals(1, temp);

    }
  }

  @Nested
  class LockingTest{

    // cara mengetestnya
    // manual jalankan test first
    // lalu jalankan test second
    // ! jangan tunggu test first selesai

    @Test
    void testLockFirst() {
      transactionOperations.executeWithoutResult(action -> {
        try{
          Product productFromDb = productRepository.searchProduct("Spoon").get(0);
          assertNotNull(productFromDb);

          Product product = productRepository.findFirstByIdEquals(productFromDb.getId()).orElse(null);
          assertNotNull(product);

          product.setPrice(10_000_000L);

          Thread.sleep(20_000);

          productRepository.save(product);
        }catch (InterruptedException e){
          throw new RuntimeException(e);
        }
      });
    }
    
    @Test
    void testLockSecond() {
      transactionOperations.executeWithoutResult(action -> {
          Product productFromDb = productRepository.searchProduct("Spoon").get(0);
          assertNotNull(productFromDb);

          Product product = productRepository.findFirstByIdEquals(productFromDb.getId()).orElse(null);
          assertNotNull(product);
          product.setPrice(20_000_000L);
          productRepository.save(product);

      });
    }
  }
  
  @Nested
  class AuditingTest{
    @Test
    void testAudit() {
      Category category = new Category();
      category.setName("Bro");

      categoryRepository.save(category);

      assertNotNull(category.getId());
      assertNotNull(category.getCreatedDate());
      assertNotNull(category.getLastModifiedDate());
    }
  }

  @Nested
  class ExampleTest {

    @Test
    void testExample() {
      Product product = new Product();
      product.setName("Spoon");

      Example<Product> example = Example.of(product);
      // example akan otomatis melakukan query sesuai dengan propeties yang kita masukan
      List<Product> products = productRepository.findAll(example);
      assertFalse(products.isEmpty());

      assertEquals(1, products.size());
      assertEquals("Spoon", products.get(0).getName());

    }

    @Test
    void testExampleWithMatchers() {
      Product product = new Product();
      product.setName("spoon");

      ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
      Example<Product> example = Example.of(product, matcher);

      // example akan otomatis melakukan query sesuai dengan propeties yang kita masukan
      List<Product> products = productRepository.findAll(example);
      assertFalse(products.isEmpty());

      assertEquals(1, products.size());
      assertEquals("Spoon", products.get(0).getName());

    }
  }

  @Nested
  class SpecificationTest{
    @Test
    void specificationTest() {

      Specification<Product> productSpecification = (root, criteriaQuery, criteriaBuilder) -> {
        return criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("name"), "Spoon"),
                        criteriaBuilder.equal(root.get("name"), "Plate")
                )
        ).getRestriction();
      };

      List<Product> products = productRepository.findAll(productSpecification);
      assertEquals(2, products.size());
    }
  }

  @Nested
  class ProjectionTest{

    @Test
    void testProjection() {

      List<SimpleProduct> products = productRepository.findAllByNameLike("%o%", SimpleProduct.class);

      assertEquals(2, products.size());
      assertEquals("Disco Lamp", products.get(0).getName());
      assertEquals("Spoon", products.get(1).getName());
    }

    @Test
    void testProjectionRecord() {
      List<SimpleProductRecord> products = productRepository.findAllByNameLike("%o%", SimpleProductRecord.class);


      assertEquals(2, products.size());
      assertEquals("Disco Lamp", products.get(0).name());
      assertEquals("Spoon", products.get(1).name());
    }
  }
}
