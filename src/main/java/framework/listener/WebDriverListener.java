package framework.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static framework.WebDriverManager.*;

public class WebDriverListener implements IClassListener, ITestListener {

    @Override
    public synchronized void onBeforeClass(ITestClass testClass) {
        quitDriver();
        startBrowser();
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        //do nothing
    }

    @Override
    public void onTestStart(ITestResult result) {
        //do nothing
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //do nothing
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                File destFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\screenshots\\"
                        + methodName + "_" + formater.format(calendar.getTime()) + ".png");
                FileUtils.copyFile(scrFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //do nothing
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //do nothing
    }

    @Override
    public void onStart(ITestContext context) {
        //do nothing
    }

    @Override
    public void onFinish(ITestContext context) {
        quitDriver();
    }

}
