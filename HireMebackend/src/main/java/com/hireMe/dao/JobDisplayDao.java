package com.hireMe.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hireMe.entity.JobEntity;
import com.hireMe.entity.JobSeekerEntity;
import com.hireMe.entity.RecruiterEntity;
import com.hireMe.model.Job;
import com.hireMe.model.JobSeeker;
import com.hireMe.model.Recruiter;

@Repository("jobDao")
@Transactional
public class JobDisplayDao implements JobDisplayDaoImpl {

	@Autowired
	SessionFactory sessionFactory;
	
	@PersistenceContext
	EntityManager em;
 
	@Override
	public List<Job> getAllJobs() {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<JobEntity> query= builder.createQuery(JobEntity.class);
		Root<JobEntity> root= query.from(JobEntity.class);
		query.select(root);
		List<JobEntity> jobListEntity= em.createQuery(query).getResultList();
		List<Job> jobList= new ArrayList<Job>();
		for(JobEntity je: jobListEntity) {
			Job job= new Job();
			job.setCompanyName(je.getCompanyName());
			job.setJid(je.getJid());
			job.setJobDesc(je.getJobDesc());
			job.setRole(je.getRole());
			
			Recruiter recruiter= new Recruiter();
			recruiter.setEmail(je.getRecruiterEntity().getEmail());
			recruiter.setName(je.getRecruiterEntity().getName());
			recruiter.setPassword(null);
			job.setRecruiter(recruiter);
			
			Set<JobSeekerEntity> applicantsEntity= je.getApplicants();
			Set<JobSeeker> applicants= new HashSet<JobSeeker>();
			for(JobSeekerEntity se: applicantsEntity) {
				JobSeeker s= new JobSeeker();
				s.setEmail(se.getEmail());
				s.setName(se.getName());
				s.setPassword(null);
				applicants.add(s);
			}
			job.setApplicants(applicants);
			jobList.add(job);
		}
		return jobList;
		
	}

	@Override
	public boolean applyJob(Job job, String sEmail) {
		Session session = sessionFactory.getCurrentSession();
		JobEntity je= session.get(JobEntity.class, job.getJid());
		JobSeekerEntity se= session.get(JobSeekerEntity.class, sEmail);
		if (je.getApplicants().contains(se)) return false;
		je.getApplicants().add(se);
		session.saveOrUpdate(je);
		return true;
	}

	@Override
	public List<Job> getJobsByRec(String rEmail) {
		Session session = sessionFactory.getCurrentSession();
		RecruiterEntity re= session.get(RecruiterEntity.class, rEmail);
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<JobEntity> query= builder.createQuery(JobEntity.class);
		Root<JobEntity> root= query.from(JobEntity.class);
		query.select(root).where(builder.equal(root.get("recruiterEntity"), re));
		List<JobEntity> jobListEntity= em.createQuery(query).getResultList();
		List<Job> jobList= new ArrayList<Job>();
		Recruiter recruiter= new Recruiter();
		recruiter.setEmail(re.getEmail());
		recruiter.setName(re.getName());
		recruiter.setPassword(null);
		for(JobEntity je: jobListEntity) {
			Job job= new Job();
			job.setCompanyName(je.getCompanyName());
			job.setJid(je.getJid());
			job.setJobDesc(je.getJobDesc());
			job.setRole(je.getRole());
			job.setRecruiter(recruiter);
			Set<JobSeekerEntity> applicantsEntity= je.getApplicants();
			Set<JobSeeker> applicants= new HashSet<JobSeeker>();
			for(JobSeekerEntity se: applicantsEntity) {
				JobSeeker s= new JobSeeker();
				s.setEmail(se.getEmail());
				s.setName(se.getName());
				s.setPassword(null);
				applicants.add(s);
			}
			job.setApplicants(applicants);
			jobList.add(job);
		}
		
		
		return jobList;

	}

	@Override
	public void addJob(Job job) {

		Session session = sessionFactory.getCurrentSession();
		JobEntity jobEntity= new JobEntity();
		jobEntity.setCompanyName(job.getCompanyName());
		jobEntity.setJobDesc(job.getJobDesc());
		jobEntity.setRole(job.getRole());
		RecruiterEntity re= session.get(RecruiterEntity.class, job.getRecruiter().getEmail());
		jobEntity.setRecruiterEntity(re);
		jobEntity.setApplicants(null);
		session.persist(jobEntity);
	}

	
}
