package ge.tbc.testautomation.listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import static com.codeborne.selenide.Selenide.screenshot;

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
        attachScreenshot(result);
    }

    private void attachScreenshot(ITestResult result) {
        try {
            // Capture screenshot as a file
            Path screenshotPath = Path.of(screenshot(OutputType.FILE).getAbsolutePath());

            // Attach screenshot to Allure report
            try (InputStream is = Files.newInputStream(screenshotPath)) {
                Allure.addAttachment(result.getName() + "_screenshot", "image/png", is, ".png");
            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to attach screenshot for test: " + result.getName());
            e.printStackTrace();
        }
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
