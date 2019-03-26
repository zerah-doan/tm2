package logic.business.ws.ows;

import framework.utils.Log;
import framework.utils.Random;
import framework.utils.Soap;
import framework.utils.Xml;
import logic.business.ws.BaseWS;

import java.io.File;

public class OWSActions extends BaseWS {
    public String customerNo;

    //region XML files
    public static final String EXAMPLE_ORDER = "src\\test\\resources\\xml\\example.xml";
    public static final String FAMILY_PERK_BUNDLE_ORDER = "src\\test\\resources\\xml\\ows\\Family_Perk_Bundle_Order.xml";
    //endregion


    public OWSActions() {
        super();
        putCommonModMap();
    }

    private void putCommonModMap() {
        String uniqueRandomString = Random.uniqueString();
        this.commonModMap.put("firstName", "firstName" + uniqueRandomString);
        this.commonModMap.put("lastName", "lastName" + uniqueRandomString);
        this.commonModMap.put("username", "username" + uniqueRandomString);
        this.commonModMap.put("emailAddress", "autotest" + uniqueRandomString + "@abc.com");
    }
    private void setCustomerNo(){
        customerNo = response.getTextByTagName("accountNumber");
    }


    public void createOrderHavingFamilyPerkBundle() {
        request = new Xml(new File(FAMILY_PERK_BUNDLE_ORDER));
        request.setTextByTagName(commonModMap);

        Log.info("Request: \n" + request.toString());
        response = Soap.sendSoapRequest(this.url, request.toSOAPMessage());
        Log.info("Response: \n" + response.toString());
        setCustomerNo();

    }


}
