package test;

import java.io.File;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestBase {
	
	public static   RestAssuredConfig config ;
	public static 	ExtentReports      extentReport ;
	public static 	ExtentSparkReporter sparkReporter ;
	public static 	ExtentTest         extentLogger ;
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://reqres.in")
				.build().contentType(ContentType.JSON);
	}

	@BeforeClass
	public static void createResponseSpecification() {
		//RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
		responseSpec = new ResponseSpecBuilder().
				expectContentType(ContentType.JSON).
				build();
	}

	@BeforeTest
	public static void setup()
	{
		extentReport = new ExtentReports() ;
		sparkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "\\Reports\\extent-report.html") );
		extentReport.attachReporter(sparkReporter) ;
		config = CurlLoggingRestAssuredConfigFactory.createConfig();
	}
	
	@AfterTest
	public void teardown()
	{
		extentReport.flush() ;

	}
}
