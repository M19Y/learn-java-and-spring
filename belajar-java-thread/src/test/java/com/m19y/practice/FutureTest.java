package com.m19y.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class FutureTest {


  @Test
  void create() throws InterruptedException, ExecutionException {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable<String> callable = () -> {
      Thread.sleep(5000);
      return "Hi";
    };

    Future<String> future = executor.submit(callable);

    while(!future.isDone()){
      System.out.println("Waiting for result");
      Thread.sleep(1000);
    }

    System.out.println(future.get());
  }



  @Test
  void testFutureCancel() throws InterruptedException, ExecutionException {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Callable<String> callable = () -> {
      Thread.sleep(5000);
      return "Hi";
    };

    Future<String> future = executor.submit(callable);

    Thread.sleep(2000);
    future.cancel(true);

    Assertions.assertTrue(future.isCancelled());
    Assertions.assertThrows(CancellationException.class, () -> System.out.println(future.get()));

  }

  @Test
  void testInvokeAllCallable() throws InterruptedException, ExecutionException {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    List<Callable<String>> callables = IntStream.range(1, 11)
            .mapToObj(value -> (Callable<String>) () -> {
              Thread.sleep(value * 500);
              return String.valueOf(value);
            }).toList();

    // execute callables one by one
//    callables.forEach(stringCallable -> executor.submit(stringCallable));

    List<Future<String>> futures = executor.invokeAll(callables);

    for (Future<String> future: futures){
      System.out.println(future.get());
    }
  }

  @Test
  void executorInvokeAnyTest() throws ExecutionException, InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(10);

    List<Callable<String>> callables = IntStream.range(1, 11)
            .mapToObj(value -> (Callable<String>) () -> {
              Thread.sleep(value * 500);
              return String.valueOf(value);
            }).toList();

    String data = executor.invokeAny(callables);
    System.out.println(data);
  }

}