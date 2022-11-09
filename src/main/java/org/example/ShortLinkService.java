package org.example;

public class ShortLinkService {

  private final ShortLinkRepository shortLinkRepository;
  private final BaseConversion baseConversion;

  public ShortLinkService(ShortLinkRepository shortLinkRepository, BaseConversion baseConversion) {
    this.shortLinkRepository = shortLinkRepository;
    this.baseConversion = baseConversion;
  }

  public String shortenUrl(String url) {
    long id = shortLinkRepository.getId();
    final ShortLink shortLink = new ShortLink(id, url);
    shortLinkRepository.save(shortLink);
    return baseConversion.encode(id);
  }

  public String resolve(String shortUrl) {
    long decode = baseConversion.decode(shortUrl);
    ShortLink shortLink = shortLinkRepository.find(decode);
    return shortLink.getDestination();
  }
}
