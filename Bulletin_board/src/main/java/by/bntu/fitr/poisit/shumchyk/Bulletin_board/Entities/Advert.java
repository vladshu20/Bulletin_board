package Entities;

import java.util.List;

public class Advert {

    private long id;
    private String text;
    private User author;
    private List<Tag> tags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Advert() {
    }

    public Advert(long id, String text, User author, List<Tag> tags) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.tags = tags;
    }
}
