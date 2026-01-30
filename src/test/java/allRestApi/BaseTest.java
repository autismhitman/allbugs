package allRestApi;

import org.testng.annotations.BeforeSuite;

import allRestApi.utils.PropertyLoader;
import allRestApi.utils.PropertyLoader;
import io.restassured.RestAssured;

public class BaseTest {
	
   @BeforeSuite
   public void setup() {
	   PropertyLoader.initialize();
	   RestAssured.baseURI= PropertyLoader.get("uri");
	   RestAssured.basePath=PropertyLoader.get("basepath");
	   
   }

}
