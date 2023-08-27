package test;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import requestandresponse.PostApiRequest;
import requestandresponse.PostApiResponse;

public class DeleteUser extends TestBase{

	String id ;
	private static PostApiResponse postResponse;

	@Test
	public void postApiTest()
	{

		PostApiRequest postRequest = new PostApiRequest();
		postRequest.setname("Hossam");
		postRequest.setjob("Senior QC");
		Response resp = RestAssured.given().spec(requestSpec).body(postRequest).when().post("/api/users");
		postResponse = resp.as(PostApiResponse.class);
		id = postResponse.getid();
		Assert.assertEquals(resp.statusCode(),201);
	}

	@Test (dependsOnMethods = {"postApiTest"} )
	public void deleteUser()
	{
		extentLogger = 	extentReport.createTest("deleteUser");
		// Due to site limitation , no record deleted after and status will be 204 (No content)
		StringWriter requestWriter = new StringWriter();
		PrintStream	requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
		Response resp = RestAssured.given().filter(new RequestLoggingFilter(requestCapture)).config(config).spec(requestSpec).when().delete("/api/users/".concat(id));
		requestCapture.flush();
		extentLogger.log(Status.INFO , "Request info is : " + requestWriter.toString() ) ;
		extentLogger.log(Status.INFO , "Response is : " + resp.asString() ) ;
		if (resp.statusCode() == 200) 
		{
			extentLogger.log(Status.PASS , "passed") ;
		}
		else 
		{
			extentLogger.log(Status.FAIL , "StatusCode is : " + resp.statusCode());
		}
		Assert.assertEquals(resp.statusCode(),200);
	}
}
