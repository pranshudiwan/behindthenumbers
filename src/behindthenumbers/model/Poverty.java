package behindthenumbers.model;

import java.math.BigDecimal;

public class Poverty {

	// 6 attributes
	protected int RecordID;								// primary key, NN, AI
	protected int Year;
	protected Integer PovertyPopulation;				// nullable
	protected BigDecimal PercentPovertyPopulation;		// nullable
	protected int AgeGroupID;							// FK, NN
	protected int CountyID;								// FK, NN
	
	
	// Constructor which takes in all attributes
	public Poverty(int RecordID, int Year, Integer PovertyPopulation, BigDecimal PercentPovertyPopulation,
			int AgeGroupID, int CountyID) {
		
		this.RecordID = RecordID;
		this.Year = Year;
		this.PovertyPopulation = PovertyPopulation;
		this.PercentPovertyPopulation = PercentPovertyPopulation;
		this.AgeGroupID = AgeGroupID;
		this.CountyID = CountyID;
	}
	
	// Constructor which takes in only RecordID
	public Poverty(int RecordID) {
		this.RecordID = RecordID;
	}

	
	// Constructor with null recordId value for auto-increment purposes.
	public Poverty(int Year, Integer PovertyPopulation, BigDecimal PercentPovertyPopulation,
			int AgeGroupID, int CountyID) {
		
		this.Year = Year;
		this.PovertyPopulation = PovertyPopulation;
		this.PercentPovertyPopulation = PercentPovertyPopulation;
		//this.ConfidenceInterval = ConfidenceInterval;
		this.AgeGroupID = AgeGroupID;
		this.CountyID = CountyID;
	}
	
	/* Setters and getters */
	
	public int getRecordID() {
		return RecordID;
	}

	public void setRecordID(int recordID) {
		RecordID = recordID;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public Integer getPovertyPopulation() {
		return PovertyPopulation;
	}

	public void setPovertyPopulation(int povertyPopulation) {
		PovertyPopulation = povertyPopulation;
	}

	public BigDecimal getPercentPovertyPopulation() {
		return PercentPovertyPopulation;
	}

	public void setPercentPovertyPopulation(BigDecimal percentPovertyPopulation) {
		PercentPovertyPopulation = percentPovertyPopulation;
	}

	public int getAgeGroupID() {
		return AgeGroupID;
	}

	public void setAgeGroupID(int ageGroupID) {
		AgeGroupID = ageGroupID;
	}

	public int getCountyID() {
		return CountyID;
	}

	public void setCountyID(int countyID) {
		CountyID = countyID;
	}
}
