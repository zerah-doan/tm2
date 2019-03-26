package suite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.config.Config;
import framework.wdm.WdManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {

    //region Hooks
    @BeforeSuite
    public void beforeSuite() {
        Config.loadEnvInfoToQueue();
        setUpReport(false);
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod(Method m) throws MalformedURLException {
        test.set(extent.createTest(m.getName()));
//        WdManager.set(WDFactory.remote(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
//        WdManager.get().get(Config.getProp("careUrl"));
    }

    @AfterMethod
    public void afterMethod() {
        Config.returnProp();
        WdManager.dismissWD();
    }
    //endregion

    //region Report
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal();

    private void setUpReport(boolean useKlov) {
        //HTML
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output\\report.html");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("TESCO MOBILE - HTML Test Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("TESCO MOBILE - HTML Test Report");

        //Klov
        KlovReporter klovReporter = new KlovReporter();
        klovReporter.initMongoDbConnection("localhost", 27017);
        klovReporter.setProjectName("TescoMobile");
        klovReporter.setReportName("Test Report Name");
        klovReporter.setKlovUrl("http://localhost");

        extent = new ExtentReports();
        if (useKlov) {
            extent.attachReporter(htmlReporter, klovReporter);
        } else {
            extent.attachReporter(htmlReporter);
        }

    }
    //endregion

}
