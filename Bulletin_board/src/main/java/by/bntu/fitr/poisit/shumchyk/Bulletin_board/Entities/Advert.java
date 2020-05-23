package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities;


import javax.persistence.*;

@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private String tag;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Advert() {
    }

    public Advert(String text, User author, String tag) {

        this.text = text;
        this.author = author;
        this.tag = tag;
    }
}
