package com.hireMe.service;

import java.util.Set;

import com.hireMe.model.Job;
import com.hireMe.model.JobSeeker;

public interface JobSeekerServiceImpl {
	
	void addUser(JobSeeker user) throws Exception;
	
	boolean checkUserExists(JobSeeker seeker);
	
	JobSeeker checkCredentials(JobSeeker seeker) throws Exception;
	
	public Set<Job> getAppliedJobs(String sEmail);
}
