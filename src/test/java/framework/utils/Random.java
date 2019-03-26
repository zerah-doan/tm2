package framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Random {
    public static String uniqueString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
