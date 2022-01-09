package net.linksguard.jobBatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class JobRestController {
	// Explication Prof M.Y Part 3 Batch Processing avec Spring Batch
		@Autowired
		private JobLauncher jobLauncher;
		@Autowired 
		private Job job1;

		 
		@GetMapping("/startJob")
		public BatchStatus load() throws Exception {
			
			Map<String, JobParameter> params =new HashMap<>();
			
			params.put("time", new JobParameter(System.currentTimeMillis()));
			JobParameters jobParameters = new JobParameters(params);
			JobExecution jobExecution = jobLauncher.run(job1, jobParameters);
			
			while(jobExecution.isRunning()){
				System.out.println("--------------------");
			}
			
			
			return jobExecution.getStatus();
		}
}
