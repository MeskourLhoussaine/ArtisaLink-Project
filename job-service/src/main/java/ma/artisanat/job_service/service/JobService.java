package ma.artisanat.job_service.service;

import ma.artisanat.job_service.model.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);
    List<Job> getAllJobs();
    List<Job> getJobsByUserId(Long userId);
    Job getJobById(Long id);
    Job updateJob(Long id, Job updatedJob);
}
