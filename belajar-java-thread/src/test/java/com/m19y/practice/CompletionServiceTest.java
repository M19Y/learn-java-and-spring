package com.m19y.practice;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

  @Test
  void testCreate() throws InterruptedException {

    ExecutorService executor = Executors.newFixedThreadPool(10);
    CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

    // thread yang akan melakukan submit
    Executors.newSingleThreadExecutor().execute(() -> {
      for (int i = 0; i < 100; i++) {
        final int index = i;
        completionService.submit(() -> {
          Thread.sleep(new Random().nextInt(2000));
          return "Task - " + index;
        });
      }
    });


    // poll task
    Executors.newSingleThreadExecutor().execute(() -> {
      while (true){
        try{
          Future<String> future = completionService.poll(5, TimeUnit.SECONDS);

          if(future == null){
            break;
          }else{
            System.out.println(future.get());
          }
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
          break;
        }
      }
    });

    executor.awaitTermination(10, TimeUnit.SECONDS);
  }
}
