package com.university.patterns.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DataSourceAdapterTest {
    private DataSourceAdapter adapter;
    private LegacyDataSource legacyDataSource;

    @BeforeEach
    public void setUp() {
        legacyDataSource = new LegacyDataSource();
        adapter = new DataSourceAdapter(legacyDataSource);
    }

    @Test
    public void testAdapterConversion() {
        List<String> modernData = adapter.getData();
        
        assertNotNull(modernData);
        assertEquals(3, modernData.size());
    }

    @Test
    public void testDataIntegrity() {
        String[] legacyData = legacyDataSource.retrieveData();
        List<String> modernData = adapter.getData();
        
        for (int i = 0; i < legacyData.length; i++) {
            assertEquals(legacyData[i], modernData.get(i));
        }
    }

    @Test
    public void testModernDataIsModifiable() {
        List<String> modernData = adapter.getData();
        modernData.add("NewData");
        
        assertEquals(4, modernData.size());
    }
}
