package org.example;

import java.util.HashMap;
import java.util.Map;

public class Base2 {

  private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final char[] allowedChars = allowedString.toCharArray();

  private static Map<Character, Integer> map = new HashMap<>();

  static {
    int i = 0;
    for (char ch : allowedChars) {
      map.put(ch, i);
      i++;
    }
  }

  private static final int base = allowedString.length();

  public String encode(long input) {
    StringBuffer sb = new StringBuffer();

    while (input > 0) {
      sb.append(allowedChars[ (int) (input % base) ]);
      input = input / base;
    }

    return sb.reverse().toString();
  }

  public long decode(String input) {
    char[] chars = input.toCharArray();
    int length = input.length(); // 62

    /*
    a -> 0
    b -> 1
    ...
    9 -> 61  61 * 62^0
    ba  -> 62 1 * 62^1 + 0 *
    bb -> 63  1 * 62^1 + 1 * 62^0
    aa -> 6
     */

    int value = 0;
    int counter = 1;
    for (char ch : chars) {
      value += map.get(ch) * Math.pow(base, length -  counter);
      counter++;
    }

    return value;
  }
}
