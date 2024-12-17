package ge.tbc.testautomation.listeners;

import ge.tbc.testautomation.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.util.Date;

public class CustomTestListener implements ITestListener {
    private long testStartTime;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test method " + result.getName() + " started at " + new Date());
        testStartTime = System.currentTimeMillis();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logTestDuration(result, "succeeded");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logTestDuration(result, "failed");
        System.out.println("Failure reason: " + result.getThrowable());
        ScreenshotUtil.captureScreenshot(result.getName());
        System.out.println("Screenshot captured for failed test: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logTestDuration(result, "skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite execution finished at: " + new Date());
    }

    private void logTestDuration(ITestResult result, String status) {
        long duration = System.currentTimeMillis() - testStartTime;
        System.out.println("Test method " + result.getName() + " " + status + ". Duration: " + duration + "ms");
    }
}
