package automation;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumDevTests extends BaseTest {
    @Test
    public void scroll1Test() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");

        Logs.debug("Generando un numero aleatorio entre 5 y 9 ");
        final var faker = new Faker();
        final var n = faker.number().numberBetween(5, 9);
        Logs.debug("n: %d", n);

        final var dynamicId = String.format("line%d", n);
        final var lineN = driver.findElement(By.id(dynamicId));

        Logs.info("Haciendo scroll hacia el id: %s", dynamicId);
        new Actions(driver)
                .scrollToElement(lineN) //hago scroll hacia el elemento
                .pause(1000)
                .perform();

        Logs.info("Haciendo click en lineN");
        lineN.click();

        Logs.info("Verificando que el texto sea: %s", dynamicId);
        Assert.assertEquals(
                driver.findElement(By.id("clicked")).getText(),
                dynamicId
        );
    }

    @Test
    public void scroll2Test() {
        Logs.info("Navegando a la página");
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_frame_out_of_view.html");

        final var iframe = driver.findElement(By.name("frame"));

        Logs.info("Haciendo scroll hacia el iframe");
        new Actions(driver)
                .scrollToElement(iframe)
                .pause(1000)
                .perform();

        Logs.debug("Cambiamos el contexto del iframe");
        driver.switchTo().frame(iframe);

        final var checkBox = driver.findElement(By.name("checkbox"));
        checkBox.click();

        Logs.info("Verificando que el checkbox esté seleccionado");
        Assert.assertTrue(checkBox.isSelected());
    }
}
