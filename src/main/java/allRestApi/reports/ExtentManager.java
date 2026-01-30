package allRestApi.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static  ExtentTest  getExtentTest() {
		return extentTest.get();
	}

	public static void setExtentTest( ExtentTest extentTestRef) {
		
		if(Objects.nonNull(extentTestRef)) {
			extentTest.set(extentTestRef);
		}
	}
	
	public static void unload() {
		extentTest.remove();
	}

}
