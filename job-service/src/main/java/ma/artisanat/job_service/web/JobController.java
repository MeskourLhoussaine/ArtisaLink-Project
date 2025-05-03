package ma.artisanat.job_service.web;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.artisanat.job_service.model.Job;
import ma.artisanat.job_service.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
@NoArgsConstructor
public class JobController {
    private  JobService jobService;

    @PostMapping
    public Job createJob( @RequestBody Job job) {
        return jobService.createJob(job);
    }
@GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
}
  @GetMapping("/user/{userId}")
    public List<Job> getJobByUserId(@PathVariable("userId") long userId) {
        return jobService.getJobsByUserId(userId);
  }
@PutMapping("/{id}")
  public Job updateJob( @PathVariable Long id ,@RequestBody Job job) {
        return jobService.updateJob(id, job);
  }
@DeleteMapping("/{id}")
  public void deleteJob( @PathVariable Long id ) {
        jobService.deleteJob(id);
  }
}
