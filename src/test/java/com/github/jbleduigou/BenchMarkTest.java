package com.github.jbleduigou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BenchMarkTest {
  
  @Test
  public void bubbleSort() {
    List<String> items = new ArrayList<>();
    items.add("JKL");
    items.add("DEF");
    items.add("ABC");
    items.add("GHI");
    
    BenchMark.bubbleSort(items);
    
    assertThat(items.toString(), is("[ABC, DEF, GHI, JKL]"));
  }
}
