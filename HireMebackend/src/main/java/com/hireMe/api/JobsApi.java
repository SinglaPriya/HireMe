package com.hireMe.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hireMe.model.Job;
import com.hireMe.service.JobDsiplayService;

@RestController
@CrossOrigin
@RequestMapping(value="/jobsApi")
public class JobsApi {
	
	@Autowired
	Environment env;
	
	@Autowired
	JobDsiplayService jobService;
	
	@GetMapping(value="/allJobs")
	public ResponseEntity<List<Job>> getAllJobs(){
		try {
			List<Job> jobs= jobService.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}
		catch(Exception e) {
			List<Job> jobs= null;

			return new ResponseEntity<List<Job>>(jobs, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value="/allJobsByRec/{rEmail}")
	public ResponseEntity<List<Job>> getJobsbyRec(@PathVariable String rEmail){
		System.out.println("hi");
		try {
			List<Job> jobs= jobService.getJobsByRec(rEmail);
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}
		catch(Exception e) {
			List<Job> jobs= null;

			return new ResponseEntity<List<Job>>(jobs, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value="/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job){
		try {
			jobService.addJob(job);
			return new ResponseEntity<String>(env.getProperty("JOB.ADD_SUCCESS"), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(env.getProperty("JOB.ADD_FAIL"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/applyJob/{sEmail}")
	public ResponseEntity<String> applyJob(@RequestBody Job job, @PathVariable("sEmail") String sEmail){
		try {
			jobService.applyJob(job, sEmail);
			System.out.println("here");
			return new ResponseEntity<String>(env.getProperty("USER.APPLIED_JOB_SUCCESS"), HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("no here");
			return new ResponseEntity<String>(env.getProperty("USER.APPLIED_JOB_FAIL"), HttpStatus.BAD_REQUEST);
		}
	}
}
