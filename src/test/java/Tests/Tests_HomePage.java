package Tests;

import Pages.HomePage;
import Utiles.Currency;
import Utiles.TestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utiles.DriverHandler.manageWebDriver;
import static Utiles.GetProperties.getConfigProperty;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class Tests_HomePage {

    private HomePage homePage;

    @BeforeMethod
    private void setUp() {
        homePage = new HomePage();
        homePage.openHomePage();
        manageWebDriver();
    }

    @DataProvider(name = "currency")
    public Object[] currency() {
        return Currency.values();
    }

    @Test(dataProvider = "currency")
    public void verifyCurrency(Currency currency) {
        setUp();
        homePage.setCurrency(currency);
        homePage.verifyCurrencyIsSelected(currency);
        ternDown();
    }

    @Test
    public void verifyAddInCart() {
        setUp();
        homePage.searchItem(getConfigProperty("itemToSearch"));
        homePage.verifySearch(getConfigProperty("itemToSearch"));
        homePage.addToCart(getConfigProperty("itemToSearch"));
        homePage.verifyItemAddedToCart(getConfigProperty("itemToSearch"));
        ternDown();
    }

    private void ternDown() {
        getWebDriver().quit();
    }

}
