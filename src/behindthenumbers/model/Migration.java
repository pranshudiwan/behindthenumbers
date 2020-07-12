package behindthenumbers.model;

import java.math.BigDecimal;

/**
 * Migration is a simple, plain old java object (POJO) representing values in an individual row in the
 * Migration table of the BehindTheNumbers SQL schema.
 *
 */
public class Migration {

	protected Integer recordId;
	protected int year;
	protected MigrationType migrationType;

	protected Integer count; // nullable type
	protected BigDecimal rate; // nullable type
	protected int countyId;

	// Enum values for each migrationType.
	public enum MigrationType {
		internationalMigration, domesticMigration, netMigrations;
	}
	
	// Constructor for Migration object.
	public Migration(Integer recordId, int year, MigrationType migrationType, 
			Integer count, BigDecimal rate, int countyId) {
		this.recordId = recordId;
		this.year = year;
		this.migrationType = migrationType;
		this.count = count;
		this.rate = rate;
		this.countyId = countyId;
	}
	
	// Constructor with null recordId value for auto-increment purposes.
	public Migration(int year, MigrationType migrationType, Integer count,
			BigDecimal rate, int countyId) {
		this.year = year;
		this.migrationType = migrationType;
		this.count = count;
		this.rate = rate;
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
	
	public MigrationType getMigrationType() {
		return migrationType;
	}
	
	public void setDayType(MigrationType migrationType) {
		this.migrationType = migrationType;
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
	
	public int getCountyId() {
		return countyId;
	}
	
	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

}
