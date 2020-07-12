/**
 * Qingyao Zheng
 * CS5200 HW4
 */

package behindthenumbers.tools;

import java.math.BigDecimal;

import behindthenumbers.dal.*;

import behindthenumbers.model.*;

import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * main() runner, used for the app demo.
 * 
 * Instructions: 1. Create a new MySQL schema and then run the CREATE TABLE
 * statements from lecture: http://goo.gl/86a11H. 2. Update ConnectionManager
 * with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		testHospitalBeds();
		testCov19();
		testAidPayment();

		System.out.println("Done");
	}

	private static void testHospitalBeds() throws SQLException {
		// Test on HospitalBeds
		HospitalBedsDao hospitalBedsDao = HospitalBedsDao.getInstance();
		HospitalBeds hospitalBeds_1 = new HospitalBeds(new BigDecimal(1.23), 1, 3);
		hospitalBedsDao.create(hospitalBeds_1);
		int hospitalBedRecordID = hospitalBeds_1.getHospitalBedRecordID();
		HospitalBeds hospitalBeds_2 = hospitalBedsDao.getHospitalBedsByHospitalBedRecordID(hospitalBedRecordID);
		hospitalBedsDao.updateBedCountPerThousandHabitantst(hospitalBeds_2, new BigDecimal(4.56));
		HospitalBeds hospitalBeds_3 = hospitalBedsDao.getHospitalBedsByHospitalBedRecordID(hospitalBedRecordID);
		assert hospitalBeds_1.getBedCountPerThousandHabitants() == new BigDecimal(1.23);
		assert hospitalBeds_2.getBedCountPerThousandHabitants() == new BigDecimal(4.56);
		assert hospitalBeds_3.getBedCountPerThousandHabitants() == new BigDecimal(4.56);
		hospitalBedsDao.delete(hospitalBeds_3);
		assert null == hospitalBedsDao.getHospitalBedsByHospitalBedRecordID(hospitalBedRecordID);
	}

	private static void testCov19() throws SQLException {
		// Test on Cov19
		Cov19Dao cov19Dao = Cov19Dao.getInstance();
		Cov19 cov19_1 = new Cov19(Date.valueOf("2020-05-31"), "Active", 1024L, 3);
		cov19Dao.create(cov19_1);
		Cov19 cov19_11 = new Cov19(Date.valueOf("2020-05-31"), "Recovered", 3L, 3);
		cov19Dao.create(cov19_11);
		int recordID = cov19_1.getRecordID();
		Cov19 cov19_2 = cov19Dao.getCov19ByRecordID(recordID);
		cov19Dao.updateCount(cov19_2, 2048L);
		Cov19 cov19_3 = cov19Dao.getCov19ByRecordID(recordID);
		assert cov19_1.getCount() == 1024L;
		assert cov19_2.getCount() == 2048L;
		assert cov19_3.getCount() == 2048L;
		cov19Dao.delete(cov19_3);
		assert null == cov19Dao.getCov19ByRecordID(recordID);

		List<Cov19> cov19_list = cov19Dao.getCov19ByCountyID(3);
		assert cov19_list.size() == 2;
	}

	private static void testAidPayment() throws SQLException {
		// Test on AidPayment
		AidPaymentDao aidPaymentDao = AidPaymentDao.getInstance();
		AidPayment aidPayment_1 = new AidPayment("Some Hospital", new BigDecimal(500.34), 3);
		aidPaymentDao.create(aidPayment_1);
		int recordID = aidPayment_1.getRecordID();
		AidPayment aidPayment_2 = aidPaymentDao.getAidPaymentByRecordID(recordID);
		aidPaymentDao.updateTotalPaymentInDollars(aidPayment_2, new BigDecimal(5000.56));
		AidPayment aidPayment_3 = aidPaymentDao.getAidPaymentByRecordID(recordID);
		assert aidPayment_1.getTotalPaymentInDollars() == new BigDecimal(500.34);
		assert aidPayment_2.getTotalPaymentInDollars() == new BigDecimal(5000.56);
		assert aidPayment_3.getTotalPaymentInDollars() == new BigDecimal(5000.56);
		aidPaymentDao.delete(aidPayment_3);
		assert null == aidPaymentDao.getAidPaymentByRecordID(recordID);
	}
}
