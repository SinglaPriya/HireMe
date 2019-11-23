package com.hireMe.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hireMe.entity.RecruiterEntity;
import com.hireMe.model.Recruiter;

@Repository("recruiterDao")
public class RecruiterDao implements RecruiterDaoImpl {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addRecruiter(Recruiter recruiter) {

		Session session= sessionFactory.getCurrentSession();
		RecruiterEntity recruiterEntity= new RecruiterEntity();
		
		recruiterEntity.setEmail(recruiter.getEmail());
		recruiterEntity.setName(recruiter.getName());
		recruiterEntity.setPassword(recruiter.getPassword());
		
		session.persist(recruiterEntity);
	}

	@Override
	public boolean checkUserExists(Recruiter recruiter) {
		Session session= sessionFactory.getCurrentSession();
		RecruiterEntity recruiterEntity = session.get(RecruiterEntity.class, recruiter.getEmail());
		if(recruiterEntity!=null) return true;
		return false;
	}

	@Override
	public Recruiter checkCredentials(Recruiter recruiter) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		RecruiterEntity recruiterEntity = session.get(RecruiterEntity.class, recruiter.getEmail());
		if(recruiterEntity!=null) {
			if(recruiterEntity.getEmail().equals(recruiter.getEmail()) && recruiterEntity.getPassword().equals(recruiter.getPassword())) {
				recruiter.setName(recruiterEntity.getName());
				
				return recruiter;
			}
		}
		throw new Exception();
	}

}
