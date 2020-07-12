package behindthenumbers.model;

import java.math.BigDecimal;

public class HospitalBeds {

	protected int hospitalBedRecordID;
	protected BigDecimal BedCountPerThousandHabitants;
	protected int BedTypeLookupTableIdID;
	protected int CountyID;

	// This constructor can be used for reading records from MySQL, where we have
	// all fields,
	// including the hospitalBedRecordID.

	public HospitalBeds(int hospitalBedRecordID, BigDecimal bedCountPerThousandHabitants, int bedTypeLookupTableIdID,
			int countyID) {
		super();
		this.hospitalBedRecordID = hospitalBedRecordID;
		BedCountPerThousandHabitants = bedCountPerThousandHabitants;
		BedTypeLookupTableIdID = bedTypeLookupTableIdID;
		CountyID = countyID;
	}

	public HospitalBeds(int hospitalBedRecordID) {
		super();
		this.hospitalBedRecordID = hospitalBedRecordID;
	}

	public HospitalBeds(BigDecimal bedCountPerThousandHabitants, int bedTypeLookupTableIdID, int countyID) {
		super();
		BedCountPerThousandHabitants = bedCountPerThousandHabitants;
		BedTypeLookupTableIdID = bedTypeLookupTableIdID;
		CountyID = countyID;
	}

	public int getHospitalBedRecordID() {
		return hospitalBedRecordID;
	}

	public void setHospitalBedRecordID(int hospitalBedRecordID) {
		this.hospitalBedRecordID = hospitalBedRecordID;
	}

	public BigDecimal getBedCountPerThousandHabitants() {
		return BedCountPerThousandHabitants;
	}

	public void setBedCountPerThousandHabitants(BigDecimal bedCountPerThousandHabitants) {
		BedCountPerThousandHabitants = bedCountPerThousandHabitants;
	}

	public int getBedTypeLookupTableIdID() {
		return BedTypeLookupTableIdID;
	}

	public void setBedTypeLookupTableIdID(int bedTypeLookupTableIdID) {
		BedTypeLookupTableIdID = bedTypeLookupTableIdID;
	}

	public int getCountyID() {
		return CountyID;
	}

	public void setCountyID(int countyID) {
		CountyID = countyID;
	}

}