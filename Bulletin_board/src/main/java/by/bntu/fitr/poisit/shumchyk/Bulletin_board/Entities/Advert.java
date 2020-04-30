package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    @OneToOne
    private User author;
    @OneToMany
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

    public Advert(String text, User author, List<Tag> tags) {

        this.text = text;
        this.author = author;
        this.tags = tags;
    }
}
