package com.hireMe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hireMe.dao.RecruiterDao;
import com.hireMe.model.Recruiter;

@Service("recruiterService")
public class RecruiterService implements RecruiterServiceImpl {

	@Autowired
	private RecruiterDao recruiterDao;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addRecruiter(Recruiter recruiterr) throws Exception {
		if(!checkUserExists(recruiterr)) recruiterDao.addRecruiter(recruiterr);	
		else throw new Exception();		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean checkUserExists(Recruiter recruiter) {
		return recruiterDao.checkUserExists(recruiter);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public Recruiter checkCredentials(Recruiter recruiter) throws Exception {
		if(checkUserExists(recruiter)) {
			return recruiterDao.checkCredentials(recruiter);
		}
		else throw new Exception();
	}

}
