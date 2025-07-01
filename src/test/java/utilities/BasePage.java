package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    private final static int defaultTimeout = 5;
    protected final SoftAssert softAssert;
    private final int timeOut;

    public BasePage(int timeOut) {
        softAssert = new SoftAssert();
        this.timeOut = timeOut;
    }

    public BasePage() {
        this(defaultTimeout);
    }

    protected WebDriver getDriver() {
        return new WebdriverProvider().get();
    }

    protected void waitPage(By locator, String pageName) {
        final var wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(this.timeOut));

        Logs.info("Esperando que la página %s cargue", pageName);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        Logs.info("%s ha cargado satisfactoriamente", pageName);
    }

    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return getDriver().findElements(locator);
    }

    public abstract void waitPageToLoad();

    public abstract void verifyPage();
}
