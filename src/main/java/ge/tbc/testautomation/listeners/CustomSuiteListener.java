package ge.tbc.testautomation.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomSuiteListener implements ISuiteListener {
    private long suiteStartTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
        System.out.println("\n========================================");
        System.out.println("Suite '" + suite.getName() + "' started at: " + LocalDateTime.now().format(formatter));
        System.out.println("========================================");
    }

    @Override
    public void onFinish(ISuite suite) {
        long suiteEndTime = System.currentTimeMillis();
        Duration duration = Duration.ofMillis(suiteEndTime - suiteStartTime);

        System.out.println("\n========================================");
        System.out.println("Suite '" + suite.getName() + "' ended at: " + LocalDateTime.now().format(formatter));
        System.out.printf("Total execution time for suite: %02d min, %02d sec%n",
                duration.toMinutes(), duration.getSeconds() % 60);
        System.out.println("========================================\n");
    }
}
