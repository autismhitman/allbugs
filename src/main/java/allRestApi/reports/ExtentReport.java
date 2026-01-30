package allRestApi.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	private static ExtentReports extent;
	
	public static void initReports() {
		
		if(Objects.isNull(extent)) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd_HH-MM-SSSS");
			String timeStamp  = sdf.format(new Date());
			extent=  new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("./reports/bug_report_"+timeStamp+".html");
			spark.config().setReportName("ExtentReport for API Testing");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("DocumentReport");			
			extent.attachReporter(spark); 
		}
		
	}
	
	public static void closeReport() {
		
		if(Objects.nonNull(extent)) {
			extent.flush();
			ExtentManager.unload();			
		}		
	}
	
	public static void createTest(String testName) {		
		ExtentManager.setExtentTest(extent.createTest(testName));
	}	

}
