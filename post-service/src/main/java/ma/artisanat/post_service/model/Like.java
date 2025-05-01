package ma.artisanat.post_service.model;



import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;


@Entity
@Builder
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Like() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public Like(Long postId, Long userId, LocalDateTime createdAt, Post post) {

        this.userId = userId;
        this.createdAt = createdAt;
        this.post = post;
    }
}
