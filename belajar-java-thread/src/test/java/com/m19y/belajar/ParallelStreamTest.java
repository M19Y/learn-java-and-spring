package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParallelStreamTest {

  @Test
  void testParallel() {

    // thread yang akan diggunakan sesuai banyak yang computer punya
    IntStream.range(0, 1000).boxed().parallel()
            .forEach(data -> System.out.println(Thread.currentThread().getName() + " : " + data));
  }

  @Test
  void testParallelUseForkJoin() {

    // kita bisa melimit menggunkana forkJoin

    ForkJoinPool pool = new ForkJoinPool(5);

    pool.execute(() -> {
      IntStream.range(0, 1000).boxed().parallel()
              .forEach(data -> System.out.println(Thread.currentThread().getName() + " : " + data));
    });
  }
}
