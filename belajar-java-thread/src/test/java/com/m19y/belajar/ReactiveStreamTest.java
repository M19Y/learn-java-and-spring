package com.m19y.belajar;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ReactiveStreamTest {


  @Test
  void testPublisherTest() throws InterruptedException {

    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
    PrintSubscriber subscriber1 = new PrintSubscriber("A", 1000L);
    PrintSubscriber subscriber2 = new PrintSubscriber("B", 2000L);

    publisher.subscribe(subscriber1);
    publisher.subscribe(subscriber2);

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    executorService.execute(() -> {
      for (int i = 0; i < 100; i++) {
        publisher.submit("Bro-" + i);
        System.out.println(Thread.currentThread().getName() + " : Send Bro-" + i);
      }
    });

    executorService.shutdown();
    executorService.awaitTermination(20, TimeUnit.SECONDS);

    Thread.sleep(100 * 1001);
  }

  @Test
  void testPublisherBufferTest() throws InterruptedException {

    ExecutorService pool = Executors.newWorkStealingPool();
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>(pool, 5);
    PrintSubscriber subscriber1 = new PrintSubscriber("A", 1000L);
    PrintSubscriber subscriber2 = new PrintSubscriber("B", 2000L);

    publisher.subscribe(subscriber1);
    publisher.subscribe(subscriber2);

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    executorService.execute(() -> {
      for (int i = 0; i < 100; i++) {
        // secara default publisher hanya bisa mengirim 256 sekaligus.
        publisher.submit("Bro-" + i);
        System.out.println(Thread.currentThread().getName() + " : Send Bro-" + i);
      }
    });

    executorService.shutdown();
    executorService.awaitTermination(20, TimeUnit.SECONDS);

    Thread.sleep(100 * 1001);
  }

  @Test
  void testProcessor() throws InterruptedException {

    // publisher
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

    // processor
    HelloProcessor processor = new HelloProcessor();

      // - processor subscribes to publisher
    publisher.subscribe(processor);

    // subscriber
    PrintSubscriber subscriber = new PrintSubscriber("A", 1000L);

      // - subscriber subscribes to processor
    processor.subscribe(subscriber);

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    executorService.execute(() -> {
      for (int i = 0; i < 100; i++) {
        // secara default publisher hanya bisa mengirim 256 sekaligus.
        publisher.submit("Bro-" + i);
        System.out.println(Thread.currentThread().getName() + " : Send Bro-" + i);
      }
    });

    executorService.shutdown();
    executorService.awaitTermination(20, TimeUnit.SECONDS);

    Thread.sleep(100 * 1001);


  }

  protected static class PrintSubscriber implements Flow.Subscriber<String>{

    private Flow.Subscription subscription;
    private String name;
    private Long sleep;

    public PrintSubscriber(String name, Long sleep) {
      this.name = name;
      this.sleep = sleep;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      // minta data dari publisher
      this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
      try {
        Thread.sleep(sleep);
        System.out.println(Thread.currentThread().getName() + " : " + name + " : " + item);
        // minta data lagi
        this.subscription.request(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void onError(Throwable throwable) {
      // kalau ada error
      throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
      // kalau sudah tidak ada data dan selesai
      System.out.println(Thread.currentThread().getName() + " : Complete");
    }
  }


  protected static class HelloProcessor
          extends SubmissionPublisher<String>
          implements Flow.Processor<String, String> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
      this.subscription = subscription;
      this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
      String value = "hello " + item;
      submit(value);
      this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
      throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
      close();
    }
  }
}
