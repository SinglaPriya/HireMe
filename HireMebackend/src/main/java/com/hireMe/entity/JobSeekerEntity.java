package com.hireMe.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table( name = "JOBSEEKER")
public class JobSeekerEntity {
	@Id
	private String email;
	private String name;
	private String password;
	
	@ManyToMany(mappedBy = "applicants")
	private Set<JobEntity> jobs;
	
	
	public Set<JobEntity> getJobs() {
		return jobs;
	}
	public void setJobs(Set<JobEntity> jobs) {
		this.jobs = jobs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
