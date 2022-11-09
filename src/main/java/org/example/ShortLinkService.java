package org.example;

public class ShortLinkService {

  private final ShortLinkRepository shortLinkRepository;
  private final Base62Conversion base62Conversion;

  public ShortLinkService(ShortLinkRepository shortLinkRepository, Base62Conversion base62Conversion) {
    this.shortLinkRepository = shortLinkRepository;
    this.base62Conversion = base62Conversion;
  }

  public String shortenUrl(String url) {
    long id = shortLinkRepository.getId();
    final ShortLink shortLink = new ShortLink(id, url);
    shortLinkRepository.save(shortLink);
    return base62Conversion.encode(id);
  }

  public String resolve(String shortUrl) {
    long decode = base62Conversion.decode(shortUrl);
    ShortLink shortLink = shortLinkRepository.find(decode);
    return shortLink.getDestination();
  }
}
