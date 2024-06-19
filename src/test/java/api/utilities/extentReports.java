package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReports implements ITestListener{
	
	String repName;
	public ExtentSparkReporter reporter;
	public ExtentReports reports=new ExtentReports();;
	public ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
		reporter=new ExtentSparkReporter(".//reports//"+repName);
		reporter.config().setDocumentTitle("Rest Assured Automation Report");
		reporter.config().setReportName("Pet Store - Store API");
		reporter.config().setTheme(Theme.DARK);
		
		reports.attachReporter(reporter); 
		reports.setSystemInfo("Application", "Pet Store - Store API");
		reports.setSystemInfo("Operating System", System.getProperty("os.name"));
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Tester", "Ketan Chauhan");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test=reports.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test=reports.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test=reports.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}

}
