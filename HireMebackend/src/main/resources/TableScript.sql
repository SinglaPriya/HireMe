-- Drop Table

DROP TABLE JOBSAPPLIED;
DROP TABLE JOB;
DROP TABLE JOBSEEKER;
DROP TABLE RECRUITER;


-- Create table
CREATE TABLE RECRUITER( 
	NAME VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) ,
	PASSWORD VARCHAR(50) NOT NULL,
	PRIMARY KEY(EMAIL)
);
---------------------------------------
 
CREATE TABLE JOBSEEKER(
	NAME VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	PRIMARY KEY(EMAIL)
);
---------------------------------------
CREATE TABLE JOB(
  JID INTEGER(4)AUTO_INCREMENT,
  COMPANYNAME VARCHAR(50) not null,
  ROLE   VARCHAR(50) not null,
  JOBDESC    VARCHAR(255) not null,
	REMAIL VARCHAR(50) NOT NULL,
  PRIMARY KEY(JID),
  FOREIGN KEY(REMAIL) REFERENCES RECRUITER(EMAIL)
) ;
---------------------------------------
 CREATE TABLE JOBSAPPLIED(
	EMAIL VARCHAR(50) NOT NULL,
  JID  INTEGER(4) not null,
  FOREIGN KEY (EMAIL) REFERENCES JOBSEEKER(EMAIL),
  FOREIGN KEY (JID) REFERENCES JOB(JID)
);
 
-- Insert values
INSERT INTO RECRUITER VALUES( "Raj","raj@example.com", "encryptedPass");
INSERT INTO RECRUITER VALUES("Rahul","rahul@example.com", "encryptedPass");

INSERT INTO JOBSEEKER VALUES ("Priya", "priya@example.com", "encryptedPass");
INSERT INTO JOBSEEKER VALUES ("Jerry", "jerry@example.com", "encryptedPass");

INSERT INTO JOB VALUES(1, "ABC Company", "Software Develop.","SD", "raj@example.com");
INSERT INTO JOB VALUES(2, "AC Company", "Software Eng.","DAFD", "raj@example.com");


-- Select Values

SELECT * FROM RECRUITER;
SELECT * FROM JOBSEEKER;
SELECT * FROM JOB;
SELECT * FROM JOBSAPPLIED;

COMMIT;
