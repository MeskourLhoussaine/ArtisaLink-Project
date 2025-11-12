package ma.artisanat.post_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shares")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sharedByUserId ;
    private Long postId;
    private String comment;
    private LocalDateTime sharedAt;
    @PrePersist
    public void onCreate() {
        this.sharedAt = LocalDateTime.now();
    }

}
