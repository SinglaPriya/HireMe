package com.hireMe.api;

import org.springframework.core.env.Environment;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hireMe.model.JobSeeker;
import com.hireMe.service.JobSeekerService;

@RestController
@CrossOrigin
@RequestMapping(value="/jobSeekerApi")
public class JobSeekerApi {
	
	@Autowired
	Environment env;
	
	
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	@PostMapping(value = "/registerSeeker")
	public ResponseEntity<String> registerSeeker(@RequestBody JobSeeker seeker){
		try {
			jobSeekerService.addUser(seeker);
			return new ResponseEntity<String>(env.getProperty("USER.REGISTERED_SUCCESS"),HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
			return new ResponseEntity<String>(env.getProperty("USER.REGISTERED_FAILED"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/loginSeeker")
	public ResponseEntity<JobSeeker> loginSeeker(@RequestBody JobSeeker seeker){
		try {
			seeker= jobSeekerService.checkCredentials(seeker);
			return new ResponseEntity<JobSeeker>(seeker, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<JobSeeker>(new JobSeeker(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getAppliedJobs/{sEmail}")
	public ResponseEntity<Set<Job>> getAppliedJobs(@PathVariable String sEmail){
		try {
			Set<Job> appliedJobs= jobSeekerService.getAppliedJobs(sEmail);
			return new ResponseEntity<Set<Job>>(appliedJobs, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Set<Job>>(new HashSet<Job>(), HttpStatus.BAD_REQUEST);
		}
	}
	

}
