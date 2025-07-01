package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class BooksPwaitTests extends BaseTest {
    @Test
    public void shadowDom1Test() {
        Logs.info("Navegando a la página");
        driver.get("https://books-pwakit.appspot.com/");

        Logs.debug("Obteniendo el shadow root");
        final var shadowRoot = driver
                .findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Obteniendo el footer a traves del shadow root");
        final var footer = shadowRoot
                .findElement(By.cssSelector("p"));

        Logs.info("Verificando que el texto sea correcto");
        Assert.assertEquals(
                footer.getText(),
                "Made with <3 by the Polymer team."
        );
    }

    @Test
    public void shadowDom2Test() {
        Logs.info("Navegando a la página");
        driver.get("https://books-pwakit.appspot.com/");

        Logs.debug("Obteniendo el shadow root");
        final var shadowRoot = driver
                .findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Obteniendo el input a traves del shadow root");
        final var input = shadowRoot.findElement(By.id("input"));

        Logs.info("Escribiendo hello world en el input");
        new Actions(driver)
                .click(input)
                .sendKeys("Hello world")
                .sendKeys(Keys.ENTER)
                .perform();

        sleep(1000);

        Logs.debug("Obteniendo el shadow root interno");
        final var shadowRootInterno = shadowRoot
                .findElement(By.cssSelector("book-explore")).getShadowRoot();

        final var listaLibros = shadowRootInterno
                .findElement(By.cssSelector("ul"));

        Logs.info("Verificando que la lista de libros sea visible");
        Assert.assertTrue(listaLibros.isDisplayed());
    }
}
