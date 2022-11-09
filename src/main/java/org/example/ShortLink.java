package org.example;

public class ShortLink {
  private final Long id;
  private final String destination;

  public ShortLink(Long id, String destination) {
    this.id = id;
    this.destination = destination;
  }

  public Long getId() {
    return id;
  }

  public String getDestination() {
    return destination;
  }
}
