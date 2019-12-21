package com.hireMe.service;

import com.hireMe.model.Recruiter;

public interface RecruiterServiceImpl {
	
	boolean checkUserExists(Recruiter recruiter);
	
	Recruiter checkCredentials(Recruiter recruiter) throws Exception;

	void addRecruiter(Recruiter recruiterr) throws Exception;
}
