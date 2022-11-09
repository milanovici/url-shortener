package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ShortLinkRepository {

  private final Map<Long, ShortLink> data = new ConcurrentHashMap<>();

  private final AtomicLong id = new AtomicLong(1);

  public long getId() {
    return id.getAndIncrement();
  }

  public void save(ShortLink entity) {
    data.put(entity.getId(), entity);
  }

  public ShortLink find(long id) {
    return data.get(id);
  }

  public int size() {
    return data.size();
  }
}
