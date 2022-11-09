//package org.example;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class BaseConversion {
//
//  private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//
//  private static final Map<Character, Integer> dictionary = new HashMap<>();
//
//  static {
//    int i = 0;
//    for (char ch : allowedString.toCharArray()) {
//      dictionary.put(ch, i);
//      ++i;
//    }
//  }
//
//  private static final int ENCODED_OUTPUT_MIN_LENGTH = 6;
//  private static final char[] allowedCharacters = allowedString.toCharArray();
//  private static final int base = dictionary.size(); // 62
//
//  public String encode(long input) {
//    var encodedString = new StringBuffer();
//
//    while (input > 0) {
//      encodedString.append(allowedCharacters[(int) (input % base)]);
//      input = input / base;
//    }
//
//    return normalize(encodedString.reverse().toString());
//  }
//
//  private String normalize(String input) {
//    if (input.length() < ENCODED_OUTPUT_MIN_LENGTH) {
//      int diff = ENCODED_OUTPUT_MIN_LENGTH - input.length();
//      input = String.valueOf(allowedCharacters[0]).repeat(diff) + input;
//    } else if (input.length() > ENCODED_OUTPUT_MIN_LENGTH) {
//      input = input.substring(0, ENCODED_OUTPUT_MIN_LENGTH);
//    }
//    return input;
//  }
//
//  public long decode(String input) {
//    var characters = input.toCharArray();
//    var length = characters.length;
//
//    var decoded = 0;
//
//    //counter is used to avoid reversing input string
//    var counter = 1;
//    for (char character : characters) {
//      decoded += dictionary.get(character) * Math.pow(base, length - counter);
//      counter++;
//    }
//    return decoded;
//  }
//
//  public static void main(String[] args) {
//    var a = new BaseConversion().decode("baa");
//    System.out.println(a);
//  }
//}