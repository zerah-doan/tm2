package logic.business.db.billing;

import framework.utils.Log;
import logic.business.db.OracleDB;

public class BillingActions extends OracleDB {
    //region CONST
    public static final String MONTHLY_PERIOD = "Monthly";
    public static final int NO_BILL_RUN_CREATED = 1;
    public static final int NO_DAY_ADJUSTED = -1;
    //endregion


    public void createNewBillingGroupWithBillRun() {
        rescheduleOtherBillRun(NO_DAY_ADJUSTED);
        String billingGroupId = createNewBillingGroup("");
        createBillRun(billingGroupId, NO_BILL_RUN_CREATED);


    }


    public void rescheduleOtherBillRun(int noDayAdjusted) {
        int i = executeUpdate("UPDATE billruncalendar br\n" +
                "SET br.asatdate          = br.asatdate + " + noDayAdjusted + ",\n" +
                "  br.rundate             = br.rundate  + " + noDayAdjusted + "\n" +
                "WHERE br.billinggroupid               IN\n" +
                "  (SELECT b.billinggroupid\n" +
                "  FROM billruncalendar b\n" +
                "  WHERE b.rundate = SYSDATE\n" +
                "  )");

        Log.info("Number of bill run rescheduled: " + i);
    }

    public String createNewBillingGroup(String billingGroupName) {
        return "";
    }

    public int createBillRun(String billingGroupId, int noBillRun) {
        return 0;
    }
}
