package com.hireMe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hireMe.model.Recruiter;
import com.hireMe.service.RecruiterService;

@RestController
@CrossOrigin
@RequestMapping("/recruiterApi")
public class RecruiterApi {
	
	@Autowired
	Environment env;
	
	@Autowired
	RecruiterService recruiterService;
	
	@PostMapping(value = "/registerRecruiter")
	public ResponseEntity<String> registerRecruiter(@RequestBody Recruiter recruiter){
		try {
			recruiterService.addRecruiter(recruiter);
			return new ResponseEntity<String>(env.getProperty("USER.REGISTERED_SUCCESS"),HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
			return new ResponseEntity<String>(env.getProperty("USER.REGISTERED_FAILED"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/loginRecruiter")
	public ResponseEntity<Recruiter> loginRecruiter(@RequestBody Recruiter recruiter){
		try {
			recruiter= recruiterService.checkCredentials(recruiter);
			return new ResponseEntity<Recruiter>(recruiter, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Recruiter>(new Recruiter(), HttpStatus.BAD_REQUEST);
		}
	}
	


}
