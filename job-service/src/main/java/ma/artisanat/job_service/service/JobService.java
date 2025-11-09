package ma.artisanat.job_service.service;

import ma.artisanat.job_service.dto.JobDto;
import ma.artisanat.job_service.model.Job;
import java.util.List;

public interface JobService {

    Job createJob(Job job);
    List<Job> getAllJobs();
    List<Job> getJobsByUserId(Long userId);
   // List<Job> getJobsByProfileId(Long profileId); //ajout de cette méthode
    JobDto getJobById(Long jobId);
    Job updateJob(Long id, Job updatedJob);
    void deleteJob(Long id);
}
