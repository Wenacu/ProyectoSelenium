package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebdriverProvider().get()).
                getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error al tomar screenshot: %s", ioException.getLocalizedMessage());
        }


    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando la evidencia anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
        } catch (IOException ioException) {
            Logs.error("Error al borrar la evidencia anterior: %s",
                    ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "failureScreenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new WebdriverProvider().get()).
                getScreenshotAs(OutputType.BYTES);
    }
}
