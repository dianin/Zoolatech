package Utiles;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DriverHandler {

    public static void manageWebDriver() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().window().maximize();
    }

}
