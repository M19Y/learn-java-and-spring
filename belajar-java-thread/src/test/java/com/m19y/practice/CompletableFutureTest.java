package com.m19y.practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {

  ExecutorService executor = Executors.newFixedThreadPool(10);

  private CompletableFuture<String> getValue(){

    CompletableFuture<String> future = new CompletableFuture<>();

    executor.execute(() -> {
      try {
        Thread.sleep(2000);
        future.complete("Nama saya budi");
      } catch (InterruptedException e) {
        future.completeExceptionally(e);
      }
    });

    return future;
  }

  @Test
  void create() throws ExecutionException, InterruptedException {
    Future<String> future = getValue();
    System.out.println(future.get());
  }

  private void execute(CompletableFuture<String> future, String value){
    executor.execute(() -> {
      try {
        Thread.sleep(1000 + new Random().nextInt(5000));
        future.complete(value);
      } catch (InterruptedException e) {
        future.completeExceptionally(e);
      }
    });
  }

  private Future<String> getFastest(){
    CompletableFuture<String> future = new CompletableFuture<>();

    execute(future, "Thread 1");
    execute(future, "Thread 2");
    execute(future, "Thread 3");

    return future;
  }

  @Test
  void getFastestTest() throws ExecutionException, InterruptedException {

    System.out.println(getFastest().get());
  }

  @Test
  void completionStageTest() throws ExecutionException, InterruptedException {

    CompletableFuture<String> future = getValue();

    CompletableFuture<String[]> futureModify = future.thenApply(data -> data.toUpperCase())
            .thenApply(data -> data.split(" "));

    String[] name = futureModify.get();
    System.out.println(Arrays.toString(name));
    for (String s : name) {
      System.out.println(s);
    }
  }
}
