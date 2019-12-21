package com.hireMe.dao;

import java.util.Set;

import com.hireMe.model.Job;
import com.hireMe.model.JobSeeker;

public interface JobSeekerDaoImpl {
	
	void addUser(JobSeeker seeker);
	
	boolean checkUserExists(JobSeeker seeker);
	
	JobSeeker checkCredentials(JobSeeker seeker) throws Exception;
	
	public Set<Job> getAppliedJobs(String sEmail);
	
}
