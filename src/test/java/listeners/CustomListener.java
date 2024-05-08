package listeners;

import com.codeborne.selenide.Screenshots;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.*;


public class CustomListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            // Capture screenshot using Selenide
            File screenshot = Screenshots.takeScreenShotAsFile();

            // Attach the screenshot to Allure report
            if (screenshot != null)
                Allure.addAttachment("Screenshot", new FileInputStream(screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}