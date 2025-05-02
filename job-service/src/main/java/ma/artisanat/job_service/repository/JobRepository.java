package ma.artisanat.job_service.repository;

import ma.artisanat.job_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPostedByUserId(Long userId);
}
