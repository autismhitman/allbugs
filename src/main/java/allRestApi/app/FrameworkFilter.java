package allRestApi.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import allRestApi.reports.ExtentManager;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class FrameworkFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
	 
		
		String requestDetails   = "<pre>" + 
			    "<b>REQUEST DETAILS:</b>\n" +
			    "<b>URI:</b> " + requestSpec.getBaseUri() + "\n" +
			    "<b>METHOD:</b> " + requestSpec.getMethod() + "\n" +
			    "<b>CONTENT TYPE:</b> " + requestSpec.getContentType() + "\n" +
			    "<b>HEADERS:</b> " + requestSpec.getHeaders().asList().toString() + "\n" +
			    "<b>BODY:</b> " + (requestSpec.getBody() == null ? "None" : requestSpec.getBody().toString()) + 
			    "</pre>";
		
		ExtentManager.getExtentTest().info(requestDetails);
		 
		Response response = ctx.next(requestSpec, responseSpec);
		
		String formattedHeaders = response.getHeaders().asList().stream()
			    .map(header -> "<b>" + header.getName() + ":</b> " + header.getValue())
			    .collect(Collectors.joining("<br>"));
 
		
	 	String responseLogs = "<pre><b>RESPONSE</b>\n" +
                "<b>Status:</b> " + response.getStatusCode() + "\n" +
	 			"<b>Content Type:</b> "+ response.getContentType()+ "\n"+
                "<b>Body:</b>\n" + response.getBody().asPrettyString() + "\n"+
                "<b>Response Headers:</b><br>" + formattedHeaders +
                "</pre>";
	 	
	 	
		ExtentManager.getExtentTest().info(responseLogs);
		
		String fileName =  System.currentTimeMillis() + ".json";
		 
	 	String filePath = "reports/" + fileName;
	 	System.out.println(filePath);
	 	try {
			Files.write(Paths.get(filePath), response.asByteArray());
		} catch (IOException e) {
			 
			e.printStackTrace();
		}

	 	// Add the link to the report
	 	ExtentManager.getExtentTest().info("<a href='" + fileName + "' target='_blank'>Click to view full JSON response file</a>");
	  
		
		return response;
	}

	
}
