package com.hireMe.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="JOB")
@GenericGenerator(name="idGen", strategy = "increment")
public class JobEntity {
	@Id
	@GeneratedValue(generator = "idGen")
	private Integer jid;
	private String companyName;
	private String role;
	private String jobDesc;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="REMAIL")
	private RecruiterEntity recruiterEntity;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "JOBSAPPLIED",
	joinColumns = @JoinColumn(name="JID"),
	inverseJoinColumns = @JoinColumn(name="EMAIL"))
	private Set<JobSeekerEntity> applicants = new HashSet<JobSeekerEntity>();

	public Set<JobSeekerEntity> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<JobSeekerEntity> applicants) {
		this.applicants = applicants;
	}
	

	public Integer getJid() {
		return jid;
	}

	public void setJid(Integer jid) {
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

	public RecruiterEntity getRecruiterEntity() {
		return recruiterEntity;
	}

	public void setRecruiterEntity(RecruiterEntity recruiterEntity) {
		this.recruiterEntity = recruiterEntity;
	}

}
