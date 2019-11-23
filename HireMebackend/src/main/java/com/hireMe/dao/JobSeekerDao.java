package com.hireMe.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hireMe.entity.JobEntity;
import com.hireMe.entity.JobSeekerEntity;
import com.hireMe.model.Job;
import com.hireMe.model.JobSeeker;

@Repository("jobSeekerDao")
public class JobSeekerDao implements JobSeekerDaoImpl {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addUser(JobSeeker seeker) {
		Session session= sessionFactory.getCurrentSession();
		JobSeekerEntity seekerEntity= new JobSeekerEntity();
		
		seekerEntity.setEmail(seeker.getEmail());
		seekerEntity.setName(seeker.getName());
		seekerEntity.setPassword(seeker.getPassword());
		
		session.persist(seekerEntity);
		
		
	}

	@Override
	public boolean checkUserExists(JobSeeker seeker) {
		Session session= sessionFactory.getCurrentSession();
		JobSeekerEntity seekerEntity = session.get(JobSeekerEntity.class, seeker.getEmail());
		if(seekerEntity!=null) return true;
		return false;
	}

	@Override
	public JobSeeker checkCredentials(JobSeeker seeker) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		JobSeekerEntity seekerEntity = session.get(JobSeekerEntity.class, seeker.getEmail());
		if(seekerEntity!=null) {
			if(seekerEntity.getEmail().equals(seeker.getEmail()) && seekerEntity.getPassword().equals(seeker.getPassword())) {
				seeker.setName(seekerEntity.getName());
				return seeker;
			}
		}
		throw new Exception();
	}

	@Override
	public Set<Job> getAppliedJobs(String sEmail) {
		Session session = sessionFactory.getCurrentSession();
		JobSeekerEntity se= session.get(JobSeekerEntity.class, sEmail);
		Set<Job> jobList= new HashSet<Job>();
		Set<JobEntity> jobListEntity= se.getJobs();
		for(JobEntity je: jobListEntity) {
			Job job = new Job();
			job.setCompanyName(je.getCompanyName());
			job.setJid(je.getJid());
			job.setJobDesc(je.getJobDesc());
			job.setRole(je.getRole());
			jobList.add(job);
		}
		return jobList;
	}

}
