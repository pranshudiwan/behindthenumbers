package behindthenumbers.model;

import java.math.BigDecimal;

public class AidPayment {

	protected int recordID;
	protected String institution;
	protected BigDecimal totalPaymentInDollars;
	protected int stateID;

	// This constructor can be used for reading records from MySQL, where we have
	// all fields,
	// including the recordID.

	public AidPayment(int recordID, String institution, BigDecimal totalPaymentInDollars, int stateID) {
		this.recordID = recordID;
		this.institution = institution;
		this.totalPaymentInDollars = totalPaymentInDollars;
		this.stateID = stateID;
	}

	public AidPayment(int recordID) {
		this.recordID = recordID;
	}

	public AidPayment(String institution, BigDecimal totalPaymentInDollars, int stateID) {
		this.institution = institution;
		this.totalPaymentInDollars = totalPaymentInDollars;
		this.stateID = stateID;
	}

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public BigDecimal getTotalPaymentInDollars() {
		return totalPaymentInDollars;
	}

	public void setTotalPaymentInDollars(BigDecimal totalPaymentInDollars) {
		this.totalPaymentInDollars = totalPaymentInDollars;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

}