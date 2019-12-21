package com.hireMe.service;

import java.util.List;

import com.hireMe.model.Job;

public interface JobDisplayServiceImpl {
	
	public List<Job> getAllJobs();
	
	public void applyJob(Job job, String sEmail) throws Exception;
	
	public List<Job> getJobsByRec(String rEmail);
	
	public void addJob(Job job);

}
