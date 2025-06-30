package automation;

import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {

    @Test
    public void pestanaTest() {
        Logs.info("Navegando a la página");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
    }
}
