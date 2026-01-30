package allRestApi.listeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import allRestApi.reports.ExtentManager;
import allRestApi.reports.ExtentReport;

public class MyListener implements ITestListener, ISuiteListener {

	public void onStart(ISuite suite) {
		
        ExtentReport.initReports();
	}

	public void onFinish(ISuite suite) {
          
		ExtentReport.closeReport();
	}

	public void onTestStart(ITestResult result) {
		
		ExtentReport.createTest(result.getMethod().getMethodName() +" is started");

	}

	public void onTestSuccess(ITestResult result) {
		
        ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + "  is passed", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + "  is failed", ExtentColor.RED));
		ExtentManager.getExtentTest().log(Status.FAIL,result.getThrowable().getMessage());
		ExtentManager.getExtentTest().info(Arrays.toString(result.getThrowable().getStackTrace()));
	}

	public void onTestSkipped(ITestResult result) {
		ExtentManager.getExtentTest().skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + "  is skipped", ExtentColor.ORANGE));
	}
 

}
