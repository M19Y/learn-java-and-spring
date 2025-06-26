package com.m19y.learn;

import com.m19y.learn.keyspace.Product;
import com.m19y.learn.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;

@SpringBootTest
public class CacheTest extends AbstractRedisTest{

  @Autowired
  private ProductService productService;

  @Test
  void testCache() {
    Cache cache = cacheManager.getCache("scores");

    // menambahkan
    cache.put("Bro", 100);
    cache.put("Sis", "lose");

    // mendapatkan
    Assertions.assertEquals(100, cache.get("Bro", Integer.class));
    Assertions.assertEquals("lose", cache.get("Sis", String.class));

    // menghapus
//    cache.evict("Bro");
//    cache.evict("Sis");

    // harusnya sudah terhapus
//    Assertions.assertNull(cache.get("Bro"));
//    Assertions.assertNull(cache.get("Sis"));

  }

  @Test
  void testCacheWithAnnotationCacheable() {
    Product product = productService.getProduct("p-02");

    Assertions.assertNotNull(product);
    Assertions.assertEquals("sample-name", product.getName());
    Assertions.assertEquals("p-02", product.getId());
    Assertions.assertEquals(10_000L, product.getPrice());

    Product product1 = productService.getProduct("p-02");

    Assertions.assertNotNull(product1);
    Assertions.assertEquals("sample-name", product1.getName());
    Assertions.assertEquals("p-02", product1.getId());
    Assertions.assertEquals(10_000L, product1.getPrice());
  }

  @Test
  void testCacheWithAnnotationCachePut() {

    Product product = new Product("001", "simple", 100L, -1L);
    productService.save(product); // langsung di simpan di cache

    // tidak perlu memanggil method lagi, karena id dari put yang dilakukan save sudah dilakukan
    Product fromCache = productService.getProduct("001");

    Assertions.assertEquals("001", fromCache.getId());
  }

  @Test
  void testCacheWithAnnotationCacheEvict() {

    Product product = new Product("001", "simple", 100L, -1L);
    productService.save(product); // langsung di simpan di cache

    // hapus dari cache
    productService.remove("001");

    // harus memanggil method lagi, karena id sudah di hapus dari chace
    Product fromCache = productService.getProduct("001");

    Assertions.assertEquals("001", fromCache.getId());

    // tidak pelu memanggil method lagi
    Product fromCache1 = productService.getProduct("001");
    Assertions.assertEquals("001", fromCache1.getId());
  }

}

