package ge.tbc.testautomation.listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReportListener implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        System.out.println("\n================== Custom Test Report ==================");

        // Loop through all suites
        for (ISuite suite : suites) {
            System.out.println("\nSuite: " + suite.getName());

            // Loop through each test in the suite
            suite.getResults().forEach((testName, suiteResult) -> {
                ITestContext testContext = suiteResult.getTestContext();
                System.out.println("Test: " + testName);

                // Print Passed Tests
                testContext.getPassedTests().getAllResults()
                        .forEach(result -> printTestResult("PASSED", result));

                // Print Failed Tests
                testContext.getFailedTests().getAllResults()
                        .forEach(result -> printTestResult("FAILED", result));

                // Print Skipped Tests
                testContext.getSkippedTests().getAllResults()
                        .forEach(result -> printTestResult("SKIPPED", result));

                // Summary of the test
                System.out.printf("Summary: PASSED=%d, FAILED=%d, SKIPPED=%d\n\n",
                        testContext.getPassedTests().size(),
                        testContext.getFailedTests().size(),
                        testContext.getSkippedTests().size());
            });
        }

        System.out.println("================== Report End ==================\n");
    }

    private void printTestResult(String status, ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.printf("  [%s] %s (%dms)\n", status, result.getName(), duration);

        if (ITestResult.FAILURE == result.getStatus()) {
            System.out.println("    Reason: " + result.getThrowable());
        }
    }
}
