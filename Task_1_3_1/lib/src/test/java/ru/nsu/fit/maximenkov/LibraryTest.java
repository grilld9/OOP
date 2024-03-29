/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.maximenkov;


import static ru.nsu.fit.maximenkov.FindSubString.findSubstring;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LibraryTest {

  @Test
  public void findInShortStringTest() throws IOException {
    StringReader reader = new StringReader("I want juice!");
    List<Integer> indexes = findSubstring(reader, "juice");
    Assertions.assertEquals(7, indexes.get(0));
  }

  @Test
  public void findInLongStringTest() throws IOException {
    StringReader reader = new StringReader("Juice is a drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables. It can also refer to liquids that are flavored with concentrate or other biological food sources, such as meat or seafood, such as clam juice. Juice is commonly consumed as a beverage or used as an ingredient or flavoring in foods or other beverages, as for smoothies. Juice emerged as a popular beverage choice after the development of pasteurization methods enabled its preservation without using fermentation (which is used in wine production).[1] The largest fruit juice consumers are New Zealand (nearly a cup, or 8 ounces, each day) and Colombia (more than three quarters of a cup each day). Fruit juice consumption on average increases with a country's income level.[2]");
    List<Integer> indexes = findSubstring(reader, "juice");
    Object[] expectedIndexes = new Object[]{247, 580, 715};
    Assertions.assertArrayEquals(expectedIndexes, indexes.toArray());
  }

  @Test
  public void findInTheBoundOfUTF8BlocksTest() throws IOException {
    StringReader reader = new StringReader("аааааабаааа");
    List<Integer> indexes = findSubstring(reader, "аба");
    Assertions.assertEquals(5, indexes.get(0));
    Assertions.assertEquals(1, indexes.size());
  }
}


