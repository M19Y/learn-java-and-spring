package com.m19y.learn;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.UUID;

public class MyTest {

  @Test
  void testRequestIdBeforeMDC() {

    String id = UUID.randomUUID().toString();

    MyController controller = new MyController(new MyService(new MyRepository()));

    MDC.put("requestId", id);
    controller.save(id);
    MDC.remove("requestId");
  }

  @Test
  void testRequestIdAfterMDC() {

    String id = UUID.randomUUID().toString();

    MyController controller = new MyController(new MyService(new MyRepository()));

    MDC.put("requestId", id);
    controller.save();
    MDC.remove("requestId");
  }

  @Test
  void testRequestIdWithDifferentThread() {

    String id = UUID.randomUUID().toString();

    MyController controller = new MyController(new MyService(new MyRepository()));

    MDC.put("requestId", id);
    new Thread(() -> {
      controller.save();
    }).start();
    MDC.remove("requestId");
  }

  @Test
  void testRequestIdWithMultiThread() throws InterruptedException {


    MyController controller = new MyController(new MyService(new MyRepository()));
    for (int i = 0; i <10; i++) {
      new Thread(() -> {
        String id = UUID.randomUUID().toString();
        MDC.put("requestId", id);
        controller.save();
        MDC.remove("requestId");
      }).start();
    }

    Thread.sleep(2000L);
  }
}
