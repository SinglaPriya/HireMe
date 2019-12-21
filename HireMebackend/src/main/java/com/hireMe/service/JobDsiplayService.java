package com.hireMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hireMe.dao.JobDisplayDao;
import com.hireMe.model.Job;

@Service("jobService")
public class JobDsiplayService implements JobDisplayServiceImpl {

	@Autowired
	JobDisplayDao jobDao;
	
	@Override
	public List<Job> getAllJobs() {
		return jobDao.getAllJobs();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void applyJob(Job job, String sEmail) throws Exception {
		if(jobDao.applyJob(job, sEmail)) return;
		throw new Exception();
		
	}

	@Override
	public List<Job> getJobsByRec(String rEmail) {
		return jobDao.getJobsByRec(rEmail);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addJob(Job job) {

		jobDao.addJob(job);
	}

}
