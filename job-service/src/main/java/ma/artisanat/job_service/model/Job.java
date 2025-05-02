package ma.artisanat.job_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long postedByUserId;
    private String status;
    private LocalDateTime postedAt;
    public Job() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPostedByUserId() {
        return postedByUserId;
    }

    public void setPostedByUserId(Long postedByUserId) {
        this.postedByUserId = postedByUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Job(LocalDateTime postedAt, String status, Long postedByUserId, String description, String title) {
        this.postedAt = postedAt;
        this.status = status;
        this.postedByUserId = postedByUserId;
        this.description = description;
        this.title = title;
    }
}
