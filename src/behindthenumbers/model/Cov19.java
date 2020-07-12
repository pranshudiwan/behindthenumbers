package behindthenumbers.model;

import java.sql.Date;

public class Cov19 {

	protected int recordID;
	protected Date date;
	protected String caseType;
	protected Long count;
	protected int countyID;

	// This constructor can be used for reading records from MySQL, where we have
	// all fields,
	// including the recordID.

	public Cov19(int recordID, Date date, String caseType, Long count, int countyID) {
		this.recordID = recordID;
		this.date = date;
		this.caseType = caseType;
		this.count = count;
		this.countyID = countyID;
	}

	public Cov19(int recordID) {
		this.recordID = recordID;
	}

	public Cov19(Date date, String caseType, Long count, int countyID) {
		this.date = date;
		this.caseType = caseType;
		this.count = count;
		this.countyID = countyID;
	}

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getCountyID() {
		return countyID;
	}

	public void setCountyID(int countyID) {
		this.countyID = countyID;
	}

}