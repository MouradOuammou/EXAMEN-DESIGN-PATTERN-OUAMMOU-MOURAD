package com.university.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.university.patterns.builder.DocumentBuilder;

public class DocumentServiceTest {
    private DocumentService documentService;

    @BeforeEach
    public void setUp() {
        documentService = new DocumentService();
    }

    @Test
    public void testCreateDocument() {
        DocumentBuilder.Document doc = documentService.createDocument(
                "Test Doc", "Author", 12, true, true
        );
        
        assertNotNull(doc);
        assertEquals("Test Doc", doc.getTitle());
        assertEquals("Author", doc.getAuthor());
    }

    @Test
    public void testDocumentCount() {
        documentService.createDocument("Doc1", "Author1", 12, false, false);
        documentService.createDocument("Doc2", "Author2", 14, true, true);
        
        assertEquals(2, documentService.getDocumentCount());
    }

    @Test
    public void testDeleteDocument() {
        DocumentBuilder.Document doc = documentService.createDocument(
                "ToDelete", "Author", 12, false, false
        );
        documentService.deleteDocument(doc);
        
        assertEquals(0, documentService.getDocumentCount());
    }

    @Test
    public void testGetAllDocuments() {
        documentService.createDocument("Doc1", "Author1", 12, false, false);
        documentService.createDocument("Doc2", "Author2", 14, true, true);
        
        assertEquals(2, documentService.getAllDocuments().size());
    }
}
