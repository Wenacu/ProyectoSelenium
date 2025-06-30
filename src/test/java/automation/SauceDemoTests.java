package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SauceDemoTests extends BaseTest {
    @Test
    public void usuarioInvalidoTest() {

        rellenarFormularioLogin("locked_out_user", "secret_sauce");

        Logs.info("Verificando el mensaje de error");
        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));

        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(),
                "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test
    public void usuarioValidoTest() {
        rellenarFormularioLogin("standard_user", "secret_sauce");

        final var inventoryList = driver.findElement(By.className("inventory_list"));

        Logs.info("Verificando que el inventory list este visible");
        Assert.assertTrue(inventoryList.isDisplayed());
    }

    @Test
    public void detalleProductoTest() {
        rellenarFormularioLogin("standard_user", "secret_sauce");

        final var imageList = driver.
                findElements(By.cssSelector("img[class='inventory_item_img']"));
        Logs.info("Haciendo click en el primer elemento de la lista");

        imageList.get(0).click();

        Logs.info("Esperando que cargue el detalle del producto");
        sleep(1000);

        Logs.info("Verificando el detalle del producto");
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_name")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_desc")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_price")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.xpath("//button[text()='Add to cart']"))
                        .isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_img")).isDisplayed()
        );
        softAssert.assertAll();
    }

    private void rellenarFormularioLogin(String username, String password) {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");

        sleep(3000);

        Logs.info("Escribiendo el username");
        driver.findElement(By.id("user-name")).sendKeys(username);

        Logs.info("Escribiendo el password");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(By.id("login-button")).click();

        sleep(2000);

    }
}

