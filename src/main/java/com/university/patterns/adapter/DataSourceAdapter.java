package com.university.patterns.adapter;

import java.util.Arrays;
import java.util.List;

public class DataSourceAdapter implements ModernDataInterface {
    private final LegacyDataSource legacyDataSource;

    public DataSourceAdapter(LegacyDataSource legacyDataSource) {
        this.legacyDataSource = legacyDataSource;
    }

    @Override
    public List<String> getData() {
        String[] data = legacyDataSource.retrieveData();
        return Arrays.asList(data);
    }
}
