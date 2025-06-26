package com.m19y.belajar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinPoolTest {

  @Test
  void create() {

    ForkJoinPool pool1 = new ForkJoinPool(5); // kita bisa mementukan berapa banyak cpu yang akan diggunakna
    ForkJoinPool pool2 = ForkJoinPool.commonPool(); // akan mengambil banyak cpu

    // menggunakan executors
    ExecutorService executorService1 = Executors.newWorkStealingPool();
    ExecutorService executorService2 = Executors.newWorkStealingPool(5);
  }

  @Test
  void testRecursiveAction() throws InterruptedException {
    ForkJoinPool pool = ForkJoinPool.commonPool();
    List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

    SimpleForkJoinTask task = new SimpleForkJoinTask(integers);
    pool.execute(task);

    pool.shutdown();
    pool.awaitTermination(20, TimeUnit.SECONDS);

  }

  @Test
  void testRecursiveTask() throws InterruptedException, ExecutionException {
    ForkJoinPool pool = ForkJoinPool.commonPool();
    List<Integer> integers = IntStream.range(0, 1000).boxed().toList();

    TotalRecursiveTask task = new TotalRecursiveTask(integers);
    ForkJoinTask<Long> submit = pool.submit(task);
    System.out.println("Total = " + task.join());

    Assertions.assertEquals(submit.get(), task.join());
    Assertions.assertEquals(task.join(), integers.stream().mapToLong(data -> data).sum());

    pool.shutdown();
    pool.awaitTermination(20, TimeUnit.SECONDS);
  }


  protected static class SimpleForkJoinTask extends RecursiveAction{

    private List<Integer> integers;

    public SimpleForkJoinTask(List<Integer> integers) {
      this.integers = integers;
    }

    @Override
    protected void compute() {
      if(integers.size() <= 10){
        // eksekusi
        doExecute();
      }else {
        // fork
        forkCompute();
      }
    }

    private void forkCompute() {
      // misal integers size adalah 10
      List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2); // ini akan mengambil 0 - 5
      List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());// ini akan mengambil 5 - 10

      SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
      SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

      ForkJoinTask.invokeAll(task1, task2);
    }

    private void doExecute() {
      integers.forEach(value -> System.out.println(Thread.currentThread().getName() + " : " + value));
    }
  }

  protected static class TotalRecursiveTask extends RecursiveTask<Long> {

    private List<Integer> integers;

    public TotalRecursiveTask(List<Integer> integers) {
      this.integers = integers;
    }

    @Override
    protected Long compute() {
      if(integers.size() <= 10){
        return doCompute();
      }else{
        return forkCompute();
      }
    }

    private Long doCompute() {
      return integers.stream().mapToLong(value -> value)
              .peek(value -> System.out.println(Thread.currentThread().getName() + " : " + value))
              .sum();
    }

    private Long forkCompute() {
      List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
      List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

      TotalRecursiveTask task1 = new TotalRecursiveTask(integers1);
      TotalRecursiveTask task2 = new TotalRecursiveTask(integers2);

      ForkJoinTask.invokeAll(task1, task2);

      return task1.join() + task2.join();
    }
  }
}
