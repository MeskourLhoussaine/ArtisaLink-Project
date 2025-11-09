package ma.artisanat.job_service.service.jobServiceImpl;

import lombok.RequiredArgsConstructor;
import ma.artisanat.job_service.client.UserClient;
import ma.artisanat.job_service.dto.JobDto;
import ma.artisanat.job_service.dto.UserDto;
import ma.artisanat.job_service.model.Job;
import ma.artisanat.job_service.enums.JobStatus;
import ma.artisanat.job_service.repository.JobRepository;
import ma.artisanat.job_service.service.JobService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserClient userClient;

    @Override
    public Job createJob(Job job) {
        // Validation des champs obligatoires
        if (job.getTitle() == null || job.getDescription() == null) {
            throw new IllegalArgumentException("Title and description are required");
        }

        // Valeur par défaut pour le statut
        if (job.getStatus() == null) {
            job.setStatus(JobStatus.OPEN);
        }

        job.setPostedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    //  Récupération de tous les jobs
    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    //  Jobs créés par un utilisateur spécifique
    @Override
    public List<Job> getJobsByUserId(Long userId) {
        return jobRepository.findByPostedByUserId(userId);
    }



    // Détails d’un job (avec infos utilisateur via Feign)
    @Override
    public JobDto getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        UserDto user = userClient.getUserById(job.getPostedByUserId());
JobStatus jobStatus = job.getStatus();
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setUserId(job.getPostedByUserId());
        jobDto.setUser(user);
        jobDto.setStatus(jobDto.getStatus()); //  enum → string
        jobDto.setPostedAt(job.getPostedAt());

        return jobDto;
    }

    //  Mise à jour d’un job
    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setStatus(updatedJob.getStatus() != null ? updatedJob.getStatus() : job.getStatus());


        return jobRepository.save(job);
    }

    //  Suppression
    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
