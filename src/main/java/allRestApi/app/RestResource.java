package allRestApi.app;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import io.restassured.response.Response;

public class RestResource {
	
	public static Response getResource() {
		
		return 	 given().spec(SpecBuilders.requestSpecBuilder())
				.filter(new FrameworkFilter())		 
				.when()		
				.get()
				.then()
				.spec(SpecBuilders.responseSpecBuilder())
				.extract().response(); 
	 
	}
	
	public static Response getResource(String endpoint) {
		
		return 	 given().spec(SpecBuilders.requestSpecBuilder())
				.filter(new FrameworkFilter())		 
				.when()		
				.get(endpoint)
				.then()
				.spec(SpecBuilders.responseSpecBuilder())
				.extract().response();		 
	 
	}
	
	public static Response postResource(Object obj) {
		
		return 	 given().spec(SpecBuilders.requestSpecBuilder())
				.body(obj)				
				.filter(new FrameworkFilter())					
				.when()		
				.post()
				.then()				
				.spec(SpecBuilders.responseSpecBuilder())
				.extract().response();
		 
	 
	}
	
	public static Response putResource(Object obj, String id) {
			
			return 	 given().spec(SpecBuilders.requestSpecBuilder())
					.body(obj)				
					.filter(new FrameworkFilter())					
					.when()		
					.put(id)
					.then()				
					.spec(SpecBuilders.responseSpecBuilder())
					.extract().response();
			 
		 
		}
	
	public static Response deleteResource(String id) {
			
			return 	 given().spec(SpecBuilders.requestSpecBuilder())				 				
					.filter(new FrameworkFilter())					
					.when()		
					.delete(id)
					.then()				
					.spec(SpecBuilders.responseSpecBuilder())
					.extract().response();
			 
		 
		}

}
