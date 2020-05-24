package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ElementCollection(targetClass = Tag.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "advert_tag", joinColumns = @JoinColumn(name = "advert_id"))
    @Enumerated(EnumType.STRING)
    private Set<Tag> tags;

    private String filename;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAuthorName() {
        if (author.getFirstName() != null){return author.getFirstName();
        }else {
            return "undifined";
        }

    }

    public String getAuthorEmail(){return author.getEmail();}

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Advert() {
    }

    public Advert(String text, User author, Set<Tag> tags) {

        this.text = text;
        this.author = author;
        this.tags = tags;
    }



}
