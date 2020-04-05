package Pages;

import Utiles.Currency;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static Utiles.GetProperties.getConfigProperty;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePage {

    private By currencyLocator                  = By.className("currency-indicator");
    private By currencySelect                   = By.id("currency_code_1");
    private By saveCurrency                     = By.xpath("//ul[@class='locale_language-selector']//button[@class='btn  regular-button regular-main-button submit submit']");
    private By productPrices                    = By.xpath("//*[@class='price product-price']");
    private By searchInput                      = By.xpath("//div[@id='search']//input[@name='substring']");
    private By productNameInProductsGrid        = By.xpath("//div[@class='products']//h5[@class='product-name']");
    private By addToCartForOpenedItem           = By.xpath("//button[@class='btn  regular-button regular-main-button add2cart submit']");
    private By titleDialogAfterAddingToCard     = By.xpath("//span[@class='ui-dialog-title']");
    private By itemNameDialogAfterAddingToCard  = By.xpath("//div[@class='item-box']//*[@class='item-name']");
    private By closeBtnDialogAfterAddingToCard  = By.className("ui-dialog-titlebar-close");
    private By miniCart                         = By.xpath("//div[@title='Your cart']");
    private By itemNameMiniCart                 = By.className("item-name");
    private By viewCartButtonMiniCart           = By.xpath("//a[@class='regular-button cart']");
    private By itemNameCart                     = By.className("item-title");
    private By quantityOfItem                   = By.xpath("//input[@title='Quantity']");

    private static final String URL             = "https://demostore.x-cart.com/";

    public void openHomePage() {
        open(URL);
    }

    public synchronized void setCurrency(Currency currency) {
        Configuration.timeout = 10000;
        $(currencyLocator).should(Condition.visible);
        $(currencyLocator).click();
        $(currencySelect).selectOptionByValue(currency.getValue());
        $(saveCurrency).submit();
    }

    public void verifyCurrencyIsSelected(Currency currency) {
        List<WebElement> productPricesLocators = getWebDriver().findElements(productPrices);
        assertThat(productPricesLocators.stream().allMatch(e -> e.getText().contains(currency.getSymbol()))).isTrue();
    }

    public void searchItem(String name) {
        $(searchInput).sendKeys(name);
        $(searchInput).submit();
        System.out.println($(productNameInProductsGrid).getText());
    }

    public void verifySearch(String name) {
        List<WebElement> productsNames = getWebDriver().findElements(productNameInProductsGrid);
        assertThat(productsNames.stream().allMatch(e -> e.getText().contains(name)));
    }

    public void addToCart(String name) {
        $(productNameInProductsGrid).click();
        $(addToCartForOpenedItem).click();
        $(titleDialogAfterAddingToCard).waitUntil(Condition.visible, 5000);
        String title = $(titleDialogAfterAddingToCard).getText();
        String itemName = $(itemNameDialogAfterAddingToCard).getText();
        assertThat(title.equals(getConfigProperty("dialogTitleAfterAddedItem"))).isTrue();
        assertThat(itemName.contains(name)).isTrue();
        $(closeBtnDialogAfterAddingToCard).click();
    }

    public void verifyItemAddedToCart(String name) {
        $(miniCart).click();
        assertThat($(itemNameMiniCart).getText().contains(name)).isTrue();
        $(viewCartButtonMiniCart).click();
        assertThat($(itemNameCart).getText().contains(name)).isTrue();
        assertThat($(quantityOfItem).getValue().equals("1")).isTrue();
    }
}





