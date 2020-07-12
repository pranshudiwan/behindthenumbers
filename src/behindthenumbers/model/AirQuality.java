package behindthenumbers.model;

/**
 * AirQuality is a simple, plain old java object (POJO) representing values in an individual row in the
 * AirQuality table of the BehindTheNumbers SQL schema.
 *
 */
public class AirQuality {

	protected Integer recordId;
	protected int year;
	protected DayType dayType;

	protected Integer count; // nullable type
	protected Integer daysInYearWithAqi; // nullable type
	protected Integer maxAqi; // nullable type
	protected int countyId;

	// Enum values for each DayType.
	public enum DayType {
		goodDays, moderateDays, unhealthyForSensitiveGroups, unhealthyDays, veryUnhealthyDays, hazardousDays;
	}
	
	// Constructor for AirQuality object.
	public AirQuality(Integer recordId, int year, DayType dayType, Integer count, Integer daysInYearWithAqi,
			Integer maxAqi, int countyId) {
		this.recordId = recordId;
		this.year = year;
		this.dayType = dayType;
		this.count = count;
		this.daysInYearWithAqi = daysInYearWithAqi;
		this.maxAqi = maxAqi;
		this.countyId = countyId;
	}
	
	// Constructor with null recordId value for auto-increment purposes.
	public AirQuality(int year, DayType dayType, Integer count, Integer daysInYearWithAqi,
			Integer maxAqi, int countyId) {
		this.year = year;
		this.dayType = dayType;
		this.count = count;
		this.daysInYearWithAqi = daysInYearWithAqi;
		this.maxAqi = maxAqi;
		this.countyId = countyId;
	}
	
	// A constructor to be used for the delete() method which indexes recordId. 
	public AirQuality(int recordId) {
		this.recordId = recordId;
	}
	

	/** Getters and setters. */
	
	public Integer getRecordId() {
		return recordId;
	}
	
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public DayType getDayType() {
		return dayType;
	}
	
	public void setDayType(DayType dayType) {
		this.dayType = dayType;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getDaysInYearWithAqi() {
		return daysInYearWithAqi;
	}
	
	public void setDaysInYearWithAqi(Integer daysInYearWithAqi) {
		this.daysInYearWithAqi = daysInYearWithAqi;
	}
	
	public Integer getMaxAqi() {
		return maxAqi;
	}
	
	public void setMaxAqi(Integer maxAqi) {
		this.maxAqi = maxAqi;
	}
	
	public int getCountyId() {
		return countyId;
	}
	
	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

}
