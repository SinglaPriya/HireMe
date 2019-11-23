package com.hireMe.model;

import java.util.Set;

public class Job {
	
	private int jid;
	private String companyName;
	private String role;
	private String jobDesc;
	private Recruiter recruiter;
	private Set<JobSeeker> applicants;
	
	
	public Set<JobSeeker> getApplicants() {
		return applicants;
	}
	public void setApplicants(Set<JobSeeker> applicants) {
		this.applicants = applicants;
	}
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

}
