package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseConversionTest {

  BaseConversion baseConversion = new BaseConversion();

  @Test
  public void testMinInteger() {
    long input = Integer.MIN_VALUE;
    String encoded = baseConversion.encode(input);
    long output = baseConversion.decode(encoded);
    assertEquals(0, output);
  }

  @Test
  public void testMaxInteger() {
    long input = Integer.MAX_VALUE;
    String encoded = baseConversion.encode(input);
    long output = baseConversion.decode(encoded);
    assertEquals(input, output);
  }

  @Test
  public void testZero() {
    long input = 0;
    String encoded = baseConversion.encode(input);
    long output = baseConversion.decode(encoded);
    assertEquals(input, output);
  }

  @Test
  public void testShouldTestConcurrency() throws InterruptedException {
    final int numberOfThreads = 10;
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    final int numberOfRequest = 10_000;
    for (int t = 0; t < numberOfThreads; ++t) {
      executorService.execute(() -> {
        for (int r = 0; r < numberOfRequest; r++) {
          String encoded = baseConversion.encode(r);
          long decoded = baseConversion.decode(encoded);
          assertEquals(r, decoded);
        }
      });
    }
    executorService.shutdown();
    executorService.awaitTermination(1000, TimeUnit.SECONDS);
  }
}