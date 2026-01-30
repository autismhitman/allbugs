package allRestApi.app;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilders {
	
	
	public static RequestSpecification requestSpecBuilder() {
		
		return new RequestSpecBuilder()
				   .setContentType(ContentType.JSON)
				   .log(LogDetail.ALL)
				   .build();
				  
			
	 }
	
	
	public static ResponseSpecification responseSpecBuilder() {
		
		return new ResponseSpecBuilder()
				   .expectContentType(ContentType.JSON)
				   .log(LogDetail.ALL)
				   .build();
 
	}

}
