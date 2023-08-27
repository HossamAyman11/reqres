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
import requestandresponse.PutApiRequest;
import requestandresponse.PutApiResponse;

public class PutApi extends TestBase{
	
	String id ;
	private static PostApiResponse postResponse;
	private static PutApiResponse putResponse;
	String postName = "Hossam" ;
	String postJob = "Senior QC" ;
	String putName = "Ahmed" ;
	String putJob = "Junior QC" ;
	
	@Test
	public void postApiTest()
	{
		
		PostApiRequest postRequest = new PostApiRequest();
        postRequest.setname(postName);
		postRequest.setjob(postJob);
		Response resp = RestAssured.given().spec(requestSpec).body(postRequest).when().post("/api/users");
		postResponse = resp.as(PostApiResponse.class);
        id = postResponse.getid();
        Assert.assertEquals(resp.statusCode(),201);
        }
	
	@Test (dependsOnMethods = {"postApiTest"} )
	public void putApiTest()
	{
		extentLogger = 	extentReport.createTest("putApiTest");
		
        PutApiRequest putRequest = new PutApiRequest();
        putRequest.setname(putName);
		putRequest.setjob(putJob);
		StringWriter requestWriter = new StringWriter();
		PrintStream	requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
		Response resp = RestAssured.given().filter(new RequestLoggingFilter(requestCapture)).config(config).spec(requestSpec).body(putRequest).when().put("/api/users/".concat(id));
		requestCapture.flush();
		putResponse = resp.as(PutApiResponse.class);
        extentLogger.log(Status.INFO , "Request info is : " + requestWriter.toString() ) ;
        extentLogger.log(Status.INFO , "Response is : " + resp.asString() ) ;
        if (resp.statusCode() == 200) 
		{
			extentLogger.log(Status.PASS , "Passed") ;
		}
		else 
		{
			extentLogger.log(Status.FAIL , "StatusCode is : " + resp.statusCode());
		}
        Assert.assertEquals(resp.statusCode(),200);
		Assert.assertEquals(putName, putResponse.getname());
		Assert.assertEquals(putJob, putResponse.getjob());
		String time = java.time.Clock.systemUTC().instant().toString();
		Assert.assertEquals(time.substring(0,16), putResponse.getupdatedAt().substring(0,16));
	}
}
