package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NavegationTests extends BaseTest {
    @Test(groups = {regression, smoke})
    public void ejercicio1Test() {
        final var url = "https://www.saucedemo.com/";

        Logs.info("Navegando a: %s", url);
        driver.get(url);

        Logs.info("Obteniendo la URL actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las urls sean iguales");
        Assert.assertEquals(currentUrl, url);
    }

    @Test(groups = {regression})
    public void ejercicio2Test() {
        final var urlHeroku = "https://the-internet.herokuapp.com/";
        final var urlGithub = "https://github.com/";

        Logs.info("Navegando a: %s", urlHeroku);
        driver.get(urlHeroku);

        Logs.info("Navegando a: %s", urlGithub);
        driver.get(urlGithub);

        Logs.info("Regresando a la página anterior");
        driver.navigate().back();

        Logs.info("Obteniendo la URL actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las urls sean iguales");
        Assert.assertEquals(currentUrl, urlHeroku);
    }

    @Test(groups = {regression})
    public void siempreFallaTest() {
        final var url = "https://the-internet.herokuapp.com/";

        Logs.info("Navegando a: %s", url);
        driver.get(url);

        Logs.info("Obteniendo la url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando las url");
        Assert.assertEquals(currentUrl, "hola mundo");
    }
}
