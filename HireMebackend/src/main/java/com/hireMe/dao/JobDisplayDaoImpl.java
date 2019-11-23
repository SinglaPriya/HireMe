package com.hireMe.dao;

import java.util.List;

import com.hireMe.model.Job;

public interface JobDisplayDaoImpl {
	
	public List<Job> getAllJobs();
	
	public boolean applyJob(Job job, String sEmail);
	
	public List<Job> getJobsByRec(String rEmail);
	
	public void addJob(Job job);
}
