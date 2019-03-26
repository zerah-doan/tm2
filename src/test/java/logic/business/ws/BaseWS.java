package logic.business.ws;

import framework.config.Config;
import framework.utils.Xml;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseWS {
    protected String url;
    protected Map<String, String> commonModMap;
    protected Xml request;
    protected Xml response;

    protected BaseWS() {
        this.url = Config.getProp("owsUrl");
        this.commonModMap = new HashMap<>();
    }
}
