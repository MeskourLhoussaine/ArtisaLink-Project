package ma.artisanat.job_service.service.jobServiceImpl;

import ma.artisanat.job_service.model.Job;
import ma.artisanat.job_service.repository.JobRepository;
import ma.artisanat.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {
    
    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job createJob(Job job) {
        job.setTitle(job.getTitle());
        job.setDescription(job.getDescription());
        job.setStatus(job.getStatus());
        job.setPostedByUserId(job.getPostedByUserId());
        job.setPostedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getJobsByUserId(Long userId) {
        return jobRepository.findByPostedByUserId(userId);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }


    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setStatus(updatedJob.getStatus());

        return jobRepository.save(job);
    }
}
