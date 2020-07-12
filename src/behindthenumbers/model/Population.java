package behindthenumbers.model;

import java.math.BigDecimal;

/**
 * Population is a simple, plain old java object (POJO) representing values in an individual row in the
 * Population table of the BehindTheNumbers SQL schema.
 *
 */
public class Population {

	protected Integer recordId;
	protected int year;
	protected PopulationChangeType populationChangeType;
	protected Integer count; // nullable type
	protected BigDecimal rate; // nullable type
	protected Integer totalPopulation; // nullable type
	protected int countyId;

	// Enum values for each populationChangeType.
	public enum PopulationChangeType {
		birth, death, naturalIncrease;
	}
	
	// Constructor for Population object.
	public Population(Integer recordId, int year, PopulationChangeType populationChangeType, 
			Integer count, BigDecimal rate, Integer totalPopulation, int countyId) {
		this.recordId = recordId;
		this.year = year;
		this.populationChangeType = populationChangeType;
		this.count = count;
		this.rate = rate;
		this.totalPopulation = totalPopulation;
		this.countyId = countyId;
	}
	
	// Constructor with null recordId value for auto-increment purposes.
	public Population(int year, PopulationChangeType populationChangeType, 
			Integer count, BigDecimal rate, Integer totalPopulation, int countyId) {
		this.year = year;
		this.populationChangeType = populationChangeType;
		this.count = count;
		this.rate = rate;
		this.totalPopulation = totalPopulation;
		this.countyId = countyId;
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
	
	public PopulationChangeType getPopulationChangeType() {
		return populationChangeType;
	}
	
	public void setPopulationChangeType(PopulationChangeType populationChangeType) {
		this.populationChangeType = populationChangeType;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public BigDecimal getRate() {
		return rate;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public Integer getTotalPopulation() {
		return totalPopulation;
	}
	
	public void setTotalPopulation(Integer totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	
	public int getCountyId() {
		return countyId;
	}
	
	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

}
