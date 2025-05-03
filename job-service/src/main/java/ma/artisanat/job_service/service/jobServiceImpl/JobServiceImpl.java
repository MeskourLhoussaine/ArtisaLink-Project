package ma.artisanat.job_service.service.jobServiceImpl;

import ma.artisanat.job_service.client.UserClient;
import ma.artisanat.job_service.dto.JobDto;
import ma.artisanat.job_service.dto.UserDto;
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

    @Autowired
    private UserClient userClient;

    @Override
    public Job createJob(Job job) {
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
    public JobDto getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        UserDto user = userClient.getUserById(job.getPostedByUserId());

        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setUserId(job.getPostedByUserId());
        jobDto.setUser(user);

        return jobDto;
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

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
