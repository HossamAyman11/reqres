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

public class GetListUsers extends TestBase{

	@Test
	public void getListUsers()

	{
		extentLogger = 	extentReport.createTest("getListUsers");
		StringWriter requestWriter = new StringWriter();
		PrintStream	requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
		Response resp = RestAssured.given().filter(new RequestLoggingFilter(requestCapture)).config(config).spec(requestSpec).when().get("/api/users?page=2");
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
