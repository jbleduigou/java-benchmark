package com.github.jbleduigou;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BenchMark {
  
  @State(Scope.Benchmark)
  public static class ExecutionPlan {
    
    public List<String> items = new ArrayList<>();
    
    @Param({"10", "100", "1000", "10000", "100000"})
    public int sizeOfList;
    
    @Setup(Level.Iteration)
    public void setUp() {
      items.clear();
      for (int i = 0; i < sizeOfList; i++) {
        items.add(randomAlphabetic(16));
      }
    }
  }
  
  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @Warmup(iterations = 5, time = 30, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.MINUTES)
  public void mergeSort(ExecutionPlan plan) {
    Collections.sort(plan.items);
  }
  
  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @Warmup(iterations = 5, time = 30, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.MINUTES)
  public void bubbleSort(ExecutionPlan plan) {
    bubbleSort(plan.items);
  }
  
  public static void bubbleSort(List<String> list) {
    int n = list.size();
    String temp;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < (n - i - 1); j++) {
        if (list.get(j).compareTo(list.get(j + 1)) > 0) {
          temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
        }
      }
    }
  }
}
