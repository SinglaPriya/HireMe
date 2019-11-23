package com.hireMe.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hireMe.dao.JobSeekerDao;
import com.hireMe.model.Job;
import com.hireMe.model.JobSeeker;

@Service("jobSeekerService")
public class JobSeekerService implements JobSeekerServiceImpl {
	
	@Autowired
	private JobSeekerDao jobSeekerDao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addUser(JobSeeker seeker) throws Exception {
		if(!checkUserExists(seeker)) jobSeekerDao.addUser(seeker);	
		else throw new Exception();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean checkUserExists(JobSeeker seeker) {
		return jobSeekerDao.checkUserExists(seeker);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public JobSeeker checkCredentials(JobSeeker seeker) throws Exception {
		if(checkUserExists(seeker)) {
			return jobSeekerDao.checkCredentials(seeker);
		}
		else throw new Exception();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public Set<Job> getAppliedJobs(String sEmail) {
		return jobSeekerDao.getAppliedJobs(sEmail);
	}

}
