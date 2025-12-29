package com.university.services;

import com.university.patterns.adapter.LegacyDataSource;
import com.university.patterns.adapter.DataSourceAdapter;
import com.university.patterns.adapter.ModernDataInterface;
import java.util.List;

public class DataAdapterService {
    private final LegacyDataSource legacySource;
    private final DataSourceAdapter adapter;

    public DataAdapterService() {
        this.legacySource = new LegacyDataSource();
        this.adapter = new DataSourceAdapter(legacySource);
    }

    public String[] getLegacyData() {
        return legacySource.retrieveData();
    }

    public List<String> getModernData() {
        return adapter.getData();
    }

    public ModernDataInterface getAdapter() {
        return adapter;
    }

    public String formatLegacyData(String[] data) {
        StringBuilder sb = new StringBuilder("Données Legacy (Array):\n");
        for (String item : data) {
            sb.append("  - ").append(item).append("\n");
        }
        return sb.toString();
    }

    public String formatModernData(List<String> data) {
        StringBuilder sb = new StringBuilder("Données adaptées (List):\n");
        for (String item : data) {
            sb.append("  - ").append(item).append("\n");
        }
        return sb.toString();
    }
}
