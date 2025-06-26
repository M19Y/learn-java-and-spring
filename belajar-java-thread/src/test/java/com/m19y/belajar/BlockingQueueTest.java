package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

  @Test
  void arrayBlockingQueue() throws InterruptedException {

    final BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executor.execute(()-> {
        try {
          queue.put("Data-" + index);
          System.out.println("Finish put data");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    // mengambil data
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          String take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(20, TimeUnit.SECONDS);
  }

  @Test
  void linkedBlockingQueue() throws InterruptedException {

    final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executor.execute(()-> {
        try {
          queue.put("Data-" + index);
          System.out.println("Finish put data");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    // mengambil data
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          String take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(20, TimeUnit.SECONDS);
  }

  @Test
  void priorityBlockingQueue() throws InterruptedException {

    final BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(10, Comparator.reverseOrder());
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executor.execute(()-> {
        try {
          queue.put(index);
          System.out.println("Finish put data");
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    // mengambil data
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          Integer take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(25, TimeUnit.SECONDS);
  }

  @Test
  void delayedQueue() throws InterruptedException {

    final BlockingQueue<ScheduledFuture<String>> queue = new DelayQueue<>();
    final ExecutorService executor = Executors.newFixedThreadPool(20);
    final ScheduledExecutorService executorSchedule = Executors.newScheduledThreadPool(10);

    for (int i = 1; i <= 10; i++) {
      final int index = i;
      Callable<String> callable = () -> "Bro " + index;
      queue.put(executorSchedule.schedule(callable, i, TimeUnit.SECONDS));
    }

    // mengambil data
    executor.execute(() -> {
      while(true){
        try {
          ScheduledFuture<String> take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take.get());
        } catch (InterruptedException | ExecutionException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(1, TimeUnit.MINUTES);
  }

  @Test
  void synchronousQueue() throws InterruptedException {

    final BlockingQueue<String> queue = new SynchronousQueue<>();
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executor.execute(()-> {
        try {
          queue.put("Data-"+ index);
          // akan menunggu sampai ada yang mengambil datanya
          System.out.println("Finish put data: " + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    // mengambil data
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          String take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(25, TimeUnit.SECONDS);
  }

  @Test
  void LinkedBlockingDeque() throws InterruptedException {

    final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      try {
        queue.putLast("Data-"+ i);
        System.out.println("Finish put data: " + i);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    // mengambil data menggunakan Last in first out
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          String take = queue.takeLast(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(25, TimeUnit.SECONDS);
  }

  @Test
  void transferQueue() throws InterruptedException {

    final TransferQueue<String> queue = new LinkedTransferQueue<>();
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executor.execute(() -> {
        try {
          queue.transfer("Data-"+ index);
          // akan menunggu pada yang mengambil datanya (sama seperti synchronousQueue
          System.out.println("Finish put data: " + index);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      });
    }

    // mengambil data menggunakan Last in first out
    executor.execute(() -> {
      while(true){
        try {
          Thread.sleep(2000);
          String take = queue.take(); // get data from queue
          System.out.println("Recive data: " + take);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
    executor.awaitTermination(25, TimeUnit.SECONDS);
  }
}
