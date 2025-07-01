package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {

    @Test
    public void pestanaTest() {
        Logs.info("Navegando a la página");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("Obteniendo el id de la pestana actual para reconocerlo anteriormente");
        final var tabId = driver.getWindowHandle();
        Logs.debug("tabId: %s", tabId);

        Logs.info("Haciendo click en el boton new tab");
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();

        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandlesSet);

        Logs.debug("Nos posicionamos en la nueva pestana");
        for (var windowHandle : windowHandlesSet) {
            //si no es el tab id original es el de la nueva pestaña
            if (!windowHandle.equals(tabId)) {
                //nos posicionamos en la nueva pestaña
                driver.switchTo().window(windowHandle);
            }
        }
        Logs.info("Verificando el texto");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed()
        );

        Logs.info("Cerrando la pestaña actual");
        driver.close();

        Logs.info("Regresando el focus a la ventana original");
        driver.switchTo().window(tabId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='Browser Windows']"))
                        .isDisplayed()
        );

    }

    @Test
    public void ventanaTest() {
        Logs.info("Navegando a la página");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("Obteniendo el id de la ventana actual para reconocerlo anteriormente");
        final var windowId = driver.getWindowHandle();
        Logs.debug("windowId: %s", windowId);

        Logs.info("Haciendo click en el boton new window");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();

        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandlesSet);

        Logs.debug("Nos posicionamos en la nueva ventana");
        for (var windowHandle : windowHandlesSet) {
            //si no es el tab id original es el de la nueva ventana
            if (!windowHandle.equals(windowId)) {
                //nos posicionamos en la nueva ventana
                driver.switchTo().window(windowHandle);
            }
        }

        Logs.info("Verificando el texto");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='New Window']"))
                        .isDisplayed()
        );

        Logs.info("Cerrando la ventana actual");
        driver.close();

        Logs.info("Regresando el focus a la ventana original");
        driver.switchTo().window(windowId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='Browser Windows']"))
                        .isDisplayed()
        );
    }

    @Test
    public void iframeTest() {
        Logs.info("Navegando a la pagina principal");
        driver.get("https://www.tutorialspoint.com/selenium/practice/nestedframes.php");

        Logs.debug("Nos posicionamos en el iframe");
        driver.switchTo().frame("frame1"); //id

        Logs.info("Verificando el titulo del iframe");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='New Tab']"))
                        .isDisplayed()
        );

        Logs.debug("Regresando a la pagina original");
        driver.switchTo().defaultContent();

        Logs.info("Verificando el titulo de la pagina");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='Nested Frames']"))
                        .isDisplayed()
        );
    }
}
