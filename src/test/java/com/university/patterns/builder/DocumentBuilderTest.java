package com.university.patterns.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentBuilderTest {
    
    @Test
    public void testBuildDocument() {
        DocumentBuilder.Document doc = new DocumentBuilder()
                .withTitle("Test Document")
                .withAuthor("John Doe")
                .withFontSize(12)
                .addHeader()
                .addFooter()
                .build();

        assertEquals("Test Document", doc.getTitle());
        assertEquals("John Doe", doc.getAuthor());
        assertEquals(12, doc.getFontSize());
        assertTrue(doc.hasHeader());
        assertTrue(doc.hasFooter());
    }
}
