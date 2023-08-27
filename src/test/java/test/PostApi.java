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


public class PostApi extends TestBase{

	
	private static PostApiResponse postResponse;
	String postName = "Hossam" ;
	String postJob = "Senior QC" ;
	
	@Test 
	public void postApiTest()
	{
		extentLogger = 	extentReport.createTest("postApiTest");
		PostApiRequest postRequest = new PostApiRequest();
        postRequest.setname(postName);
		postRequest.setjob(postJob);
		StringWriter requestWriter = new StringWriter();
		PrintStream	requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
		Response resp = RestAssured.given().filter(new RequestLoggingFilter(requestCapture)).config(config).spec(requestSpec).body(postRequest).when().post("/api/users");
		requestCapture.flush();
        postResponse = resp.as(PostApiResponse.class);
        extentLogger.log(Status.INFO , "Request info is : " + requestWriter.toString() ) ;
		extentLogger.log(Status.INFO , "Response is : " + resp.asString() ) ;
		if (resp.statusCode() == 201) 
		{
			extentLogger.log(Status.PASS , "Passed") ;
		}
		else 
		{
			extentLogger.log(Status.FAIL , "StatusCode is : " + resp.statusCode());
		}
		Assert.assertEquals(resp.statusCode(),201);
        Assert.assertEquals(postName, postResponse.getname());
		Assert.assertEquals(postJob, postResponse.getjob());
		String time = java.time.Clock.systemUTC().instant().toString();
	    Assert.assertEquals(time.substring(0,16), postResponse.getcreatedAt().substring(0,16));
	    
	}
}
