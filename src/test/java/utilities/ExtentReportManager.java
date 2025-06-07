package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Desktop;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    String repName;

    public void onStart(ITestContext testContext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        repName="Test-Report-"+ timestamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specify location of the report
        sparkReporter.config().setDocumentTitle("Automation Test Report"); //Title of the report
        sparkReporter.config().setReportName("OpenCart Functional Testing"); //name of the report
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System info
        extent.setSystemInfo("Application","opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        String os= testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operation System", os);
        
        String browser= testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
      List<String> includedGroups=  testContext.getCurrentXmlTest().getIncludedGroups();
      if(!includedGroups.isEmpty()) {
    	  extent.setSystemInfo("Groups", includedGroups.toString());
      }
    }
    
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName()); // Declare and initialize test
        test.assignCategory(result.getMethod().getGroups()); // Optional group info
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    
    public void onTestFailure(ITestResult result) {
    	test=extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.FAIL, result.getName()+ " got failed");
    	test.log(Status.INFO, result.getThrowable().getMessage());
    	
    	try {
    		String imgpath= new BaseClass().captureScreen(result.getName());
    		test.addScreenCaptureFromPath(imgpath);
    	}
    	catch(IOException e1) {
    		e1.printStackTrace();
    	}
    	
    }
    
    public void onTestSkipped(ITestResult result) {
    	test=extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.SKIP, result.getName()+ " got skipped");
    	test.log(Status.INFO, result.getThrowable().getMessage());
    }
    
    public void onFinish(ITestContext testContext) {
    	extent.flush();
    	
    	String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
    	File extentReport= new File(pathOfExtentReport);
    	
    	try {
    		Desktop.getDesktop().browse(extentReport.toURI());
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    

    }
    
    

   
}
