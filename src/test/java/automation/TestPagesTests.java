package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.io.File;

public class TestPagesTests extends BaseTest {
    @Test
    public void subirFileTest() {
        Logs.info("Navegar a la página");
        driver.get("https://testpages.eviltester.com/styled/file-upload-test.html");

        final var file = new File("src/test/resources/imagen/messi.jpeg/");

        Logs.info("Subiendo imagen");
        driver.findElement(By.id("fileInput")).sendKeys(file.getAbsolutePath());

        Logs.info("Seleccionando la imagen");
        driver.findElement(By.id("itsanimage")).click();

        Logs.info("Haciendo click en el botón verde");
        driver.findElement(By.name("upload")).click();

        Logs.info("Verificando que se subió la imagen");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='You uploaded this image:']"))
                        .isDisplayed()
        );
    }
}
