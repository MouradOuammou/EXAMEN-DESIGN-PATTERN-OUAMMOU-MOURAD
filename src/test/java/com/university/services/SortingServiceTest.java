package com.university.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingServiceTest {
    private SortingService sortingService;

    @BeforeEach
    public void setUp() {
        sortingService = new SortingService();
    }

    @Test
    public void testQuickSortStrategy() {
        List<Integer> data = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        SortingService.SortResult<Integer> result = sortingService.sortWithStrategy(data, "quick");
        
        assertEquals(Arrays.asList(1, 2, 5, 8, 9), result.getSortedData());
        assertEquals("quick", result.getStrategy());
    }

    @Test
    public void testBubbleSortStrategy() {
        List<Integer> data = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        SortingService.SortResult<Integer> result = sortingService.sortWithStrategy(data, "bubble");
        
        assertEquals(Arrays.asList(1, 2, 5, 8, 9), result.getSortedData());
        assertEquals("bubble", result.getStrategy());
    }

    @Test
    public void testSortingPerformance() {
        List<Integer> data = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        SortingService.SortResult<Integer> result = sortingService.sortWithStrategy(data, "quick");
        
        assertTrue(result.getDurationNanos() >= 0);
        assertTrue(result.getDurationMillis() >= 0);
    }

    @Test
    public void testInvalidStrategy() {
        List<Integer> data = new ArrayList<>(Arrays.asList(5, 2));
        
        assertThrows(IllegalArgumentException.class, () -> {
            sortingService.sortWithStrategy(data, "invalid");
        });
    }
}
