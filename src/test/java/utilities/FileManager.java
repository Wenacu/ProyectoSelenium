package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final static String screenshotPath = "src/test/resources/screenshots";
    private static final String pageStructurePath = "src/test/resources/pageStructure";

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

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando page source");

        final var path = String.format("%s/%s.html", pageStructurePath, fileName);
        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres si no existen");
            if (file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebdriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch (IOException ioException) {
            Logs.error("Error tomar page source %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando la evidencia anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
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

    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageStructurePath() {
        return Jsoup.parse(new WebdriverProvider().get().getPageSource()).html();
    }

}
