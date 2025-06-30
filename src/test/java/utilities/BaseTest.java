package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected final String smoke = "smoke";
    protected final String regression = "regression";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Inicializando el driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando driver al web driver provider");
        new WebdriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Matando el driver");
        driver.quit();
    }

    protected void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException interruptedException) {
            Logs.error("InterruptedException: %s",
                    interruptedException.getLocalizedMessage());
        }
    }
}
