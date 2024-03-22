package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import noCommerce.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extentReports = ExtentReportConfig.getReportObject();
    ExtentTest test;

    // Solves the concurrency issue of  "test" object while running parallel, tests, so we make it thread safe
    // if you dont make it threadsafe, then one method will fail and failure will be shown on some other method
    // because different threads will access same object.
    ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result){
        // create test for ExtentReports object
        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocalExtentTest.set(test); // assign a unique thread id to each "test" object
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    /*
    * On Failure: print the error message
    * and attach the screenshot to the extent report
    * */
    @Override
    public void onTestFailure(ITestResult result){
        threadLocalExtentTest.get().fail(result.getThrowable()); // prints the error msg in extent reports
        try{
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e){
            e.printStackTrace();
        }

        // take screenshot and attach it to report
        String pathOfScreenshot = getScreenshot(result.getMethod().getMethodName(), driver);
        threadLocalExtentTest.get().addScreenCaptureFromPath(pathOfScreenshot, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context){
        extentReports.flush();
    }

}
