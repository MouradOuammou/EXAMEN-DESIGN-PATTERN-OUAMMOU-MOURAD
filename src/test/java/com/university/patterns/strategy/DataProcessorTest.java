package com.university.patterns.strategy;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DataProcessorTest {
    
    @Test
    public void testQuickSort() {
        DataProcessor processor = new DataProcessor();
        processor.setSortingStrategy(new QuickSort());
        
        List<Integer> data = Arrays.asList(5, 2, 8, 1, 9);
        processor.process(data);
        
        assertEquals(Arrays.asList(1, 2, 5, 8, 9), data);
    }

    @Test
    public void testBubbleSort() {
        DataProcessor processor = new DataProcessor();
        processor.setSortingStrategy(new BubbleSort());
        
        List<Integer> data = Arrays.asList(5, 2, 8, 1, 9);
        processor.process(data);
        
        assertEquals(Arrays.asList(1, 2, 5, 8, 9), data);
    }
}
