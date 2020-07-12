package behindthenumbers.model;

import java.math.BigDecimal;

public class Education {

	protected int RecordID;							// primary key, NN, AI
	protected int Year;
	protected int EducationLevelID;					// FK, NN				
	//protected String EducationLevel;
	protected Integer NumberOfPeople;				// nullable
	protected BigDecimal Percentage;				// nullable
	protected int CountyID;							// FK, NN
	
	
	// Constructor 1
		public Education(int RecordID, int Year, int EducationLevelID,
				Integer NumberOfPeople, BigDecimal Percentage, int CountyID) {
			
			this.RecordID = RecordID;
			this.Year = Year;
			this.EducationLevelID = EducationLevelID;
			//this.EducationLevel = EducationLevel;
			this.NumberOfPeople = NumberOfPeople;
			this.Percentage = Percentage;
			this.CountyID = CountyID;
		}
		
		// Constructor 2
		public Education(int RecordID) {
			this.RecordID = RecordID;
		}

		
		// Constructor 3
		public Education(int Year, int EducationLevelID, 
				Integer NumberOfPeople, BigDecimal Percentage, int CountyID) {
			
			this.Year = Year;
			this.EducationLevelID = EducationLevelID;
			//this.EducationLevel = EducationLevel;
			this.NumberOfPeople = NumberOfPeople;
			this.Percentage = Percentage;
			this.CountyID = CountyID;
		}

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

		public int getEducationLevelID() {
			return EducationLevelID;
		}

		public void setEducationLevelID(int educationLevelID) {
			EducationLevelID = educationLevelID;
		}

	/*	public String getEducationLevel() {
			return EducationLevel;
		}

		public void setEducationLevel(String educationLevel) {
			EducationLevel = educationLevel;
		}
*/
		public Integer getNumberOfPeople() {
			return NumberOfPeople;
		}

		public void setNumberOfPeople(Integer numberOfPeople) {
			NumberOfPeople = numberOfPeople;
		}

		public BigDecimal getPercentage() {
			return Percentage;
		}

		public void setPercentage(BigDecimal percentage) {
			Percentage = percentage;
		}

		public int getCountyID() {
			return CountyID;
		}

		public void setCountyID(int countyID) {
			CountyID = countyID;
		}
}
