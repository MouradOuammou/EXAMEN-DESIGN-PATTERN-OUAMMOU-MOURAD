package com.university.services;

import com.university.patterns.strategy.DataProcessor;
import com.university.patterns.strategy.SortingStrategy;
import com.university.patterns.strategy.BubbleSort;
import com.university.patterns.strategy.QuickSort;
import java.util.List;

public class SortingService {
    private final DataProcessor processor;

    public SortingService() {
        this.processor = new DataProcessor();
    }

    public <T extends Comparable<T>> SortResult<T> sortWithStrategy(List<T> data, String strategyName) {
        SortingStrategy strategy = getStrategy(strategyName);
        processor.setSortingStrategy(strategy);

        long startTime = System.nanoTime();
        processor.process(data);
        long duration = System.nanoTime() - startTime;

        return new SortResult<>(data, strategyName, duration);
    }

    private SortingStrategy getStrategy(String strategyName) {
        return switch (strategyName.toLowerCase()) {
            case "bubble" -> new BubbleSort();
            case "quick" -> new QuickSort();
            default -> throw new IllegalArgumentException("Stratégie inconnue: " + strategyName);
        };
    }

    public static class SortResult<T> {
        private final List<T> sortedData;
        private final String strategy;
        private final long durationNanos;

        public SortResult(List<T> sortedData, String strategy, long durationNanos) {
            this.sortedData = sortedData;
            this.strategy = strategy;
            this.durationNanos = durationNanos;
        }

        public List<T> getSortedData() { return sortedData; }
        public String getStrategy() { return strategy; }
        public long getDurationNanos() { return durationNanos; }
        public double getDurationMillis() { return durationNanos / 1_000_000.0; }
    }
}
