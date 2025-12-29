package com.university.patterns.builder;

public class DocumentBuilder {
    private String title;
    private String content;
    private String author;
    private int fontSize;
    private boolean hasHeader;
    private boolean hasFooter;

    public DocumentBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public DocumentBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public DocumentBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public DocumentBuilder withFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public DocumentBuilder addHeader() {
        this.hasHeader = true;
        return this;
    }

    public DocumentBuilder addFooter() {
        this.hasFooter = true;
        return this;
    }

    public Document build() {
        return new Document(title, content, author, fontSize, hasHeader, hasFooter);
    }

    public static class Document {
        private final String title;
        private final String content;
        private final String author;
        private final int fontSize;
        private final boolean hasHeader;
        private final boolean hasFooter;

        public Document(String title, String content, String author, int fontSize, 
                       boolean hasHeader, boolean hasFooter) {
            this.title = title;
            this.content = content;
            this.author = author;
            this.fontSize = fontSize;
            this.hasHeader = hasHeader;
            this.hasFooter = hasFooter;
        }

        @Override
        public String toString() {
            return "Document{" +
                    "title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", fontSize=" + fontSize +
                    ", hasHeader=" + hasHeader +
                    ", hasFooter=" + hasFooter +
                    '}';
        }

        // Getters
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String getAuthor() { return author; }
        public int getFontSize() { return fontSize; }
        public boolean hasHeader() { return hasHeader; }
        public boolean hasFooter() { return hasFooter; }
    }
}
