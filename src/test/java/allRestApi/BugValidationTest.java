package allRestApi;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import allRestApi.app.Apis;
import allRestApi.pojos.Bug;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;


@Epic("Bug Functionality")
public class BugValidationTest extends BaseTest{
	
	@Feature("Bug Retrieval")
	@Description("Able to retrieve all bugs")
	@Test
	public void fetchAllBugs() {		
	 Response response= Apis.getAllBugDetails();	
	 Allure.step("Getting the response: "+ response.asPrettyString());
	 Assertions.assertThat(response.statusCode()).isEqualTo(200); 
	 List<Bug> allBugs = response.as(new TypeRef<List<Bug>>() {});
	 Allure.step("Getting the All bugs: "+ allBugs.toString());
	 Assertions.assertThat(allBugs).hasSizeGreaterThan(20);
 
	}
	
	@Feature("Bug Creation")
	@Description("Able to create new bug")
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
		 
	     Allure.step("Bug details" + bug.toString());
		 Response response= Apis.createNewBug(bug);	 
		 Allure.step("Getting the response: "+ response.asPrettyString());
		 Assertions.assertThat(response.statusCode()).isEqualTo(201);		 
		 String bugId = response.jsonPath().get("bugId");
		 Allure.step("Getting the response bug id: "+ bugId);
		 context.setAttribute("bugId", bugId);
	 
 
	}
	
	@DataProvider(name="getData")
	public Object[][] createData() {
		
		return new Object[][] {
			
			{"Shankar Jones", "Medium", 2, "Button not working in my profile section",false}
			
		};
	}
	
	@Feature("Bug Modification")
	@Description("Able to modify the existing bug")
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
		
		 Allure.step("Bug details" + bug.toString());
	  
		 Response response= Apis.updateBugId(bug,"/"+ context.getAttribute("bugId"));	
		 Allure.step("Getting the response: "+ response.asPrettyString());
		 Assertions.assertThat(response.statusCode()).isEqualTo(200);		 
		 String bugId = response.jsonPath().get("bugId");
		 context.setAttribute("bugId", bugId);
		 Allure.step("Getting the response bug id: "+ bugId);
 
	}
	
	@DataProvider 
	public Object[][] updateData() {
		
		return new Object[][] {
			
			{"Shankar JonesUpdate1", "High", 2, "Button not working in my profile section",false}
			
		};
	}
	
	@Feature("Bug Retrieval by Id")
	@Description("Able to retrieve bug by Id")
	@Test
	public void fetchBugById(ITestContext context) {
		
	 Response response= Apis.getBugById("/"+ context.getAttribute("bugId"));
	 Allure.step("Getting the response: "+ response.asPrettyString());
	 Assertions.assertThat(response.statusCode()).isEqualTo(200);
	 
  
	}
	
	@Feature("Bug Deletion")
	@Description("Able to delete bug by Id")
	@Test
	public void deleteBugById(ITestContext context) {
 
	 Response response= Apis.deleteBug("/"+ context.getAttribute("bugId"));
	 Allure.step("Getting the response: "+ response.asPrettyString());
	 Assertions.assertThat(response.statusCode()).isEqualTo(200); 
	 Assertions.assertThat(response.jsonPath().getString("message")).isEqualTo("Bug deleted");
	 Assertions.assertThat(response.jsonPath().getString("bugId")).isEqualTo(context.getAttribute("bugId"));
	 Allure.step("Getting the response bug id: "+ response.jsonPath().getString("bugId"));
  
	}
	
	
	@Feature("Bug Retrieval")
	@Description("Able to retrieve all bugs and compare the size")
	@Test
	public void totalCountBugs() {		
	 Response response= Apis.getAllBugDetails();	
	 Allure.step("Getting the response: "+ response.asPrettyString());
	 Assertions.assertThat(response.statusCode()).isEqualTo(200); 
	 List<Bug> allBugs = response.as(new TypeRef<List<Bug>>() {});
	 Allure.step("Getting the All bugs: "+ allBugs.toString());
	 Assertions.assertThat(allBugs).hasSizeGreaterThan(26);
 
	}
}
