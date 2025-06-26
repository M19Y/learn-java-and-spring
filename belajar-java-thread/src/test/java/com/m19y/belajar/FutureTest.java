package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

  @Test
  void testFuture() throws InterruptedException, ExecutionException {

    ExecutorService singleThread = Executors.newSingleThreadExecutor();

    Callable<String> callable = new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.sleep(5000);
        return "Hi";
      }
    };

    Future<String> future = singleThread.submit(callable);
    System.out.println("Start");

    while(!future.isDone()){
      Thread.sleep(1000);
      System.out.println("Waiting progress");
    }
    System.out.println(future.get());
    System.out.println("Finish");

  }

  @Test
  void testFutureCancel() throws InterruptedException{

    ExecutorService singleThread = Executors.newSingleThreadExecutor();

    Future<String> future = singleThread.submit(() -> {
        Thread.sleep(5000);
        return "Hi";
    });

    System.out.println("Start");

    Thread.sleep(2000);
    future.cancel(true);
    try {
      System.out.println(future.get());
    } catch (CancellationException e){
      System.out.println("future data has been canceled");
    } catch (ExecutionException e) {
      System.out.println("Execution failed: " + e.getMessage());
    }
    System.out.println("Finish");

    singleThread.shutdown();
  }

  @Test
  void testInvokeAny() throws InterruptedException, ExecutionException {

    ExecutorService fixExecutor = Executors.newFixedThreadPool(10);

    List<Callable<String>> callables = IntStream.range(1, 11)
        .mapToObj(value -> (Callable<String>) () -> String.valueOf(value)).collect(Collectors.toList());

    System.out.println(fixExecutor.invokeAny(callables));
    fixExecutor.shutdown();
  }

  @Test
  void testInvokeAll() throws InterruptedException {

    ExecutorService fixExecutor = Executors.newFixedThreadPool(10);

    List<Callable<String>> callables = IntStream.range(1, 11)
        .mapToObj(value -> new Callable<String>() {
          @Override
          public String call() throws Exception {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
          }
        }).collect(Collectors.toList());

    // execute satu persatu
    /* callables.forEach(callable -> {
      Future<String> future= fixExecutor.submit(callable);
      try {
        System.out.println(future.get());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }

    });
    */

    List<Future<String>> futures = fixExecutor.invokeAll(callables);

    futures.forEach(data -> {
      try {
        System.out.println(data.get());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
    });

    fixExecutor.shutdown();
  }
}
