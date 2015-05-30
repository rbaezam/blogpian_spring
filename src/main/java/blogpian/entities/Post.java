package blogpian.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rodolfo on 5/25/15.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 200)
    private String title;
    @Column(length = 10000)
    private String body;
    private Date createdAt;
    private Boolean isPublished;

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.createdAt = new Date();
        this.isPublished = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }
}
