package behindthenumbers.model;

import java.math.BigDecimal;

public class Employment {

	// 9 attributes
	protected int EmploymentRecordID;									// PK, NN, AI
	protected Integer Year;												// nullable
	protected Integer EmployedPopulation;								// nullable
	protected Integer UnemployedPopulation;								// nullable
	protected BigDecimal UnemployedRate;								// nullable
	protected Integer MedianHouseHoldIncomeInDollars;					// nullable
	protected BigDecimal MedianHouseHoldIncomePercentageOfStateTotal;	// nullable
	protected Integer CivilianLaborForceAnnualAverage;					// nullable
	protected int CountyID;												// FK, NN
	
	
	// Constructor 1
	public Employment(int EmploymentRecordID, Integer Year, Integer EmployedPopulation, 
			Integer UnemployedPopulation, BigDecimal UnemployedRate, Integer MedianHouseHoldIncomeInDollars, 
			BigDecimal MedianHouseHoldIncomePercentageOfStateTotal, Integer CivilianLaborForceAnnualAverage, 
			int CountyID) {
		
		this.EmploymentRecordID = EmploymentRecordID;
		this.Year = Year;
		this.EmployedPopulation = EmployedPopulation;
		this.UnemployedPopulation = UnemployedPopulation;
		this.UnemployedRate = UnemployedRate;
		this.MedianHouseHoldIncomeInDollars = MedianHouseHoldIncomeInDollars;
		this.MedianHouseHoldIncomePercentageOfStateTotal = MedianHouseHoldIncomePercentageOfStateTotal;
		this.CivilianLaborForceAnnualAverage = CivilianLaborForceAnnualAverage;
		this.CountyID = CountyID;
	}
	
	
	// Constructor 2
	public Employment(int EmploymentRecordID) {
		this.EmploymentRecordID = EmploymentRecordID;
	}
	
	
	// Constructor 3
	public Employment(Integer Year, Integer EmployedPopulation, Integer UnemployedPopulation, 
			BigDecimal UnemployedRate, Integer MedianHouseHoldIncomeInDollars, 
			BigDecimal MedianHouseHoldIncomePercentageOfStateTotal, Integer CivilianLaborForceAnnualAverage,
			int CountyID) {
		
		
		this.Year = Year;
		this.EmployedPopulation = EmployedPopulation;
		this.UnemployedPopulation = UnemployedPopulation;
		this.UnemployedRate = UnemployedRate;
		this.MedianHouseHoldIncomeInDollars = MedianHouseHoldIncomeInDollars;
		this.MedianHouseHoldIncomePercentageOfStateTotal = MedianHouseHoldIncomePercentageOfStateTotal;
		this.CivilianLaborForceAnnualAverage = CivilianLaborForceAnnualAverage;
		this.CountyID = CountyID;
	}


	public int getEmploymentRecordID() {
		return EmploymentRecordID;
	}


	public void setEmploymentRecordID(int employmentRecordID) {
		EmploymentRecordID = employmentRecordID;
	}


	public Integer getYear() {
		return Year;
	}


	public void setYear(Integer year) {
		Year = year;
	}


	public Integer getEmployedPopulation() {
		return EmployedPopulation;
	}


	public void setEmployedPopulation(Integer employedPopulation) {
		EmployedPopulation = employedPopulation;
	}


	public Integer getUnemployedPopulation() {
		return UnemployedPopulation;
	}


	public void setUnemployedPopulation(Integer unemployedPopulation) {
		UnemployedPopulation = unemployedPopulation;
	}


	public BigDecimal getUnemployedRate() {
		return UnemployedRate;
	}


	public void setUnemployedRate(BigDecimal unemployedRate) {
		UnemployedRate = unemployedRate;
	}


	public Integer getMedianHouseHoldIncomeInDollars() {
		return MedianHouseHoldIncomeInDollars;
	}


	public void setMedianHouseHoldIncomeInDollars(Integer medianHouseHoldIncomeInDollars) {
		MedianHouseHoldIncomeInDollars = medianHouseHoldIncomeInDollars;
	}


	public BigDecimal getMedianHouseHoldIncomePercentageOfStateTotal() {
		return MedianHouseHoldIncomePercentageOfStateTotal;
	}


	public void setMedianHouseHoldIncomePercentageOfStateTotal(BigDecimal medianHouseHoldIncomePercentageOfStateTotal) {
		MedianHouseHoldIncomePercentageOfStateTotal = medianHouseHoldIncomePercentageOfStateTotal;
	}


	public Integer getCivilianLaborForceAnnualAverage() {
		return CivilianLaborForceAnnualAverage;
	}


	public void setCivilianLaborForceAnnualAverage(Integer civilianLaborForceAnnualAverage) {
		CivilianLaborForceAnnualAverage = civilianLaborForceAnnualAverage;
	}


	public int getCountyID() {
		return CountyID;
	}


	public void setCountyID(int countyID) {
		CountyID = countyID;
	}
}
