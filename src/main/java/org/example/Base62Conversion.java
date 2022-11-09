package org.example;

import java.util.HashMap;
import java.util.Map;

public class Base62Conversion {

  //abcdefghijklmnopqrstuvwxyz
  private static final String dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  private static final Map<Character, Integer> dictionaryMap = new HashMap<>();

  static {
    int i = 0;
    for (char ch : dictionary.toCharArray()) {
      dictionaryMap.put(ch, i);
      i++;
    }
  }

  private static final char[] allowedChars = dictionary.toCharArray();

  private static final int base = allowedChars.length;

  public String encode(long input) {
    StringBuffer sb = new StringBuffer();

    while (input > 0) {
      sb.append(allowedChars[(int) (input % base)]);
      input = input / base;
    }

    return normalize(sb.reverse().toString());
  }

  public String normalize(String input) {
    if (input.length() < 6) {
      int diff = 6 - input.length();
      input = String.valueOf(allowedChars[0]).repeat(diff) + input;
    } else if (input.length() > 6) {
      input = input.substring(0, 6);
    }
    return input;
  }

  public long decode(final String input) {
    char[] chars = input.toCharArray();
    int length = input.length();

    // 2 * 62^2 + 0 * 62^1 + 0 * 62^0
    long output = 0;
    int counter = 1;
    for (char ch : chars) {
      output += dictionaryMap.get(ch) * Math.pow(base, length - counter);
      counter++;
    }

    return output;
  }
}
