package com.university.patterns.strategy;

import java.util.List;

public class DataProcessor {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy strategy) {
        this.sortingStrategy = strategy;
    }

    public <T extends Comparable<T>> void process(List<T> data) {
        if (sortingStrategy == null) {
            throw new IllegalStateException("Sorting strategy not set");
        }
        sortingStrategy.sort(data);
    }
}
