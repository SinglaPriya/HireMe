package com.hireMe.dao;

import com.hireMe.model.Recruiter;

public interface RecruiterDaoImpl {

	void addRecruiter(Recruiter recruiter);
	
	boolean checkUserExists(Recruiter recruiter);
	
	Recruiter checkCredentials(Recruiter recruiter) throws Exception;
}
