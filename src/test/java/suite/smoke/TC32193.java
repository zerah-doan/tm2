package suite.smoke;

import logic.business.db.billing.BillingActions;
import org.testng.annotations.Test;
import suite.BaseTest;

public class TC32193 extends BaseTest {

    @Test
    public void TC32193_Self_Care_WS_Subscription_is_Active_Family_perk_Bundle(){
//        OWSActions ows = new OWSActions();
//        ows.createOrderHavingFamilyPerkBundle();
//        System.out.println(ows.customerNo);

        BillingActions billing = new BillingActions();
        billing.createNewBillingGroupWithBillRun();

    }


}
