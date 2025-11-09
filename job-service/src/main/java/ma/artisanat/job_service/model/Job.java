package ma.artisanat.job_service.model;

import jakarta.persistence.*;
import lombok.*;
import ma.artisanat.job_service.enums.JobStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long postedByUserId;
    //private Long profileId;
    @Enumerated(EnumType.STRING) //  pour sauvegarder le nom de l’enum (et pas l’index)
    private JobStatus status;
    private LocalDateTime postedAt;

    public JobStatus getStatus() {
        return status;
    }
}
