package allRestApi.app;

import io.restassured.response.Response;

public class Apis {
	
	
	public static Response getAllBugDetails() {
		
		 return RestResource.getResource();
	}
	
	public static Response getBugById(String endPoint) {
		
		 return RestResource.getResource(endPoint);
	}

	public static Response createNewBug(Object obj) {
		
		 return RestResource.postResource(obj);
	}
	
	public static Response updateBugId(Object obj, String id) {
		
		 return RestResource.putResource(obj, id);
	}
	
	public static Response deleteBug(String id) {
		
		 return RestResource.deleteResource(id);
	}
	
	
}
