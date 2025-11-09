package ma.artisanat.job_service.dto;

import lombok.*;
import ma.artisanat.job_service.model.Job;
import ma.artisanat.job_service.enums.JobStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
 @Setter
public class JobDto {

    private Long id;
    private String title;
    private String description;
    private String location;
    private Long userId;
    private Long profileId;
    private JobStatus status; //  Enum au lieu de String
    private LocalDateTime postedAt;
    private UserDto user; //Informations sur l’utilisateur (via Feign Client)

    // Méthode pratique pour convertir un Job → JobDto
    public static JobDto fromEntity(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(null) // tu peux l’ajouter plus tard dans l’entité Job si besoin
                .userId(job.getPostedByUserId())

                .status(job.getStatus())
                .postedAt(job.getPostedAt())
                .build();
    }

    //  Et inversement : JobDto → Job (utile pour création/mise à jour)
    public Job toEntity() {
        return Job.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .postedByUserId(this.userId)

                .status(this.status)
                .postedAt(this.postedAt)
                .build();
    }
}
