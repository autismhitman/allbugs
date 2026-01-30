package allRestApi;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import allRestApi.app.Apis;
import allRestApi.pojos.Bug;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
public class BugValidationTest extends BaseTest{
	
	
	@Test
	public void fetchAllBugs() {
		
	 Response response= Apis.getAllBugDetails();	 
	 Assertions.assertThat(response.statusCode()).isEqualTo(200); 
	 List<Bug> allBugs = response.as(new TypeRef<List<Bug>>() {});
	 Assertions.assertThat(allBugs).hasSizeGreaterThan(20);
 
	}
	
	@Test(dataProvider="getData")
	public void createBug(ITestContext context, String createdBy, String severity,
			int priority, String title, boolean completed) {
		Bug bug = Bug
			    .builder()
			 	.createdBy(createdBy)
			 	.severity(severity)
			 	.priority(priority)
			 	.title(title)
			 	.completed(completed)
			    .build();
		 
	  
		 Response response= Apis.createNewBug(bug);	 
		 Assertions.assertThat(response.statusCode()).isEqualTo(201);
		 
		 String bugId = response.jsonPath().get("bugId");
		 context.setAttribute("bugId", bugId);
	 
 
	}
	
	@DataProvider(name="getData")
	public Object[][] createData() {
		
		return new Object[][] {
			
			{"Shankar Jones", "Medium", 2, "Button not working in my profile section",false}
			
		};
	}
	
	@Test(dataProvider="updateData")
	public void updateBug(ITestContext context, String createdBy, String severity,
			int priority, String title, boolean completed) {
		Bug bug = Bug
			    .builder()
			 	.createdBy(createdBy)
			 	.severity(severity)
			 	.priority(priority)
			 	.title(title)
			 	.completed(completed)
			    .build();
		 
	  
		 Response response= Apis.updateBugId(bug,"/"+ context.getAttribute("bugId"));	 
		 Assertions.assertThat(response.statusCode()).isEqualTo(200);		 
		 String bugId = response.jsonPath().get("bugId");
		 context.setAttribute("bugId", bugId);
	 
 
	}
	
	@DataProvider 
	public Object[][] updateData() {
		
		return new Object[][] {
			
			{"Shankar JonesUpdate", "High", 2, "Button not working in my profile section",false}
			
		};
	}
	
	@Test
	public void fetchBugById(ITestContext context) {
		
	 Response response= Apis.getBugById("/"+ context.getAttribute("bugId"));
	 
	 Assertions.assertThat(response.statusCode()).isEqualTo(200);
  
	}
	
	@Test
	public void deleteBugById(ITestContext context) {
 
	 Response response= Apis.deleteBug("/"+ context.getAttribute("bugId"));
	 
	 Assertions.assertThat(response.statusCode()).isEqualTo(200); 
	 Assertions.assertThat(response.jsonPath().getString("message")).isEqualTo("Bug deleted");
	 Assertions.assertThat(response.jsonPath().getString("bugId")).isEqualTo(context.getAttribute("bugId"));
	 
  
	}
	
	
	

}
