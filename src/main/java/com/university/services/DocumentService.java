package com.university.services;

import com.university.patterns.builder.DocumentBuilder;
import java.util.ArrayList;
import java.util.List;

public class DocumentService {
    private final List<DocumentBuilder.Document> documents = new ArrayList<>();

    public DocumentBuilder.Document createDocument(String title, String author, 
                                                   int fontSize, boolean header, boolean footer) {
        DocumentBuilder builder = new DocumentBuilder()
                .withTitle(title)
                .withAuthor(author)
                .withFontSize(fontSize);

        if (header) builder.addHeader();
        if (footer) builder.addFooter();

        DocumentBuilder.Document doc = builder.build();
        documents.add(doc);
        return doc;
    }

    public List<DocumentBuilder.Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    public void deleteDocument(DocumentBuilder.Document doc) {
        documents.remove(doc);
    }

    public int getDocumentCount() {
        return documents.size();
    }
}
