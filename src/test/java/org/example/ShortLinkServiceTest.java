package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortLinkServiceTest {

  private final ShortLinkRepository shortLinkRepository = new ShortLinkRepository();

  private final Base62Conversion base62Conversion = new Base62Conversion();

  private final ShortLinkService shortLinkService = new ShortLinkService(shortLinkRepository, base62Conversion);

  // duplicates have different short url
  @Test
  public void shortUrlShouldHave6Characters() {
    String url = "www.google.com/hello-world-123";
    String shortUrl = shortLinkService.shortenUrl(url);
    assertEquals(shortUrl.length(), 6);
  }

  @Test
  public void test10MillionUrls() {
    final String url = "www.google.com/hello-world-123";
    for (int i = 0; i < 10_000_000; i++) {
      String shortUrl = shortLinkService.shortenUrl(url);
      String resolvedUrl = shortLinkService.resolve(shortUrl);
      assertEquals(url, resolvedUrl);
    }
  }

  @Test
  public void testShouldTestConcurrency() throws InterruptedException {
    final int numberOfThreads = 10;
    ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    final int numberOfRequest = 10_000;
    for (int a = 0; a < numberOfThreads; ++a) {
      executorService.execute(() -> {
        for (int i = 0; i < numberOfRequest; i++) {
          String url1 = "https://www.google.com/hello-world-123" + (i);
          String shortUrl = shortLinkService.shortenUrl(url1);
          String resolvedUrl = shortLinkService.resolve(shortUrl);
          assertEquals(url1, resolvedUrl);
        }
      });
    }
    executorService.shutdown();
    executorService.awaitTermination(1000, TimeUnit.SECONDS);
    assertEquals(numberOfThreads * numberOfRequest + 1, shortLinkRepository.getId());
    assertEquals(numberOfThreads * numberOfRequest, shortLinkRepository.size());
  }
}