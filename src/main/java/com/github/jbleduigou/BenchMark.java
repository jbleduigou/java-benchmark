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

    @Param({ "1000", "5000", "10000" })
    public int sizeOfList;

    @Setup(Level.Iteration)
    public void setUp() {
      items.clear();
      for (int i = 0 ; i < sizeOfList ; i++) {
         items.add(randomAlphabetic(16));
      }
      System.out.println(items.size());
    }
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
  @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
  public void collectionSort(ExecutionPlan plan) {
    Collections.sort(plan.items);
  }
  
}
