package ru.apphub.client.model;

public class Comment {
    private Long id;
    private String payload;

    public Comment(Long id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
