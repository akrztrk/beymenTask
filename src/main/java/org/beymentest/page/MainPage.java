package org.beymentest.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.beymentest.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertTrue;

public class MainPage extends BasePage {

    public static final By BEYMEN_SEARCH_INPUT = By.cssSelector("input[class=\"default-input o-header__search--input\"]");
    public static final By BEYMEN_MY_ACCOUNT_BUTTON = By.cssSelector("a[class=\"o-header__userInfo--item bwi-account-o -customer\"]");
    public static final By BEYMEN_FAVOURITES_BUTTON = By.cssSelector("a[class=\"o-header__userInfo--item bwi-favorite-o -favorite\"]");
    public static final By BEYMEN_CART_BUTTON = By.cssSelector("a[class=\"o-header__userInfo--item bwi-cart-o -cart\"]");
    public static final By BEYMEN_SHOW_MORE_BUTTON = By.id("moreContentButton");
    public static final By BEYMEN_RANDOM_PRODUCT = By.cssSelector("div[class=\"o-productList__item\"]");
    public static final By BEYMEN_PRODUCT_SIZE = By.xpath("//span[@class=\"m-variation__item\"  or @class=\"m-variation__item -criticalStock\"]");
    public static final By BEYMEN_ADD_CART_BUTTON = By.cssSelector("button[class=\"m-addBasketFavorite__basket btn\"]");
    public static final By BEYMEN_PRODUCT_PRICE = By.id("priceNew");
    public static final By BEYMEN_AMOUNT_IN_CART = By.id("quantitySelect0");
    public static final By BEYMEN_REMOVE_ITEM = By.id("removeCartItemBtn0");
    public static final By BEYMEN_CHECK_CART_EMPTY = By.id("emtyCart");
    public static final By BEYMEN_PRODUCT_PRICE_IN_CART = By.className("m-productPrice__salePrice");

    private static Logger logger = (Logger) LogManager.getLogger(MainPage.class);
    private String productPrice;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage beklemeSuresi(int time) throws InterruptedException {
        Thread.sleep(time*1000);
        return this;
    }

    public MainPage anaSayfaKontrol() {
        assertTrue("Homepage not opened.", findElement(BEYMEN_SEARCH_INPUT).isDisplayed());
        logger.debug("Homepage loaded.");
        return this;
    }

    public MainPage hesabımKontrol() {
        assertTrue("My account not found.", findElement(BEYMEN_MY_ACCOUNT_BUTTON).isDisplayed());
        logger.debug("My account button found.");
        return this;
    }

    public MainPage favorilerimKontrol() {
        assertTrue("Favorites not found.", findElement(BEYMEN_FAVOURITES_BUTTON).isDisplayed());
        logger.debug("Favorites found.");
        return this;
    }

    public MainPage sepetimKontrol() {
        assertTrue("My cart not found..", findElement(BEYMEN_CART_BUTTON).isDisplayed());
        logger.debug("My cart found.");
        return this;
    }

    public MainPage urunArama(String productName) {
        findElement(BEYMEN_SEARCH_INPUT).sendKeys(productName);
        findElement(BEYMEN_SEARCH_INPUT).sendKeys(Keys.ENTER);
        logger.debug(productName + " product name searched");
        return this;
    }

    public MainPage sayfaSonuScroll() throws InterruptedException {
        Thread.sleep(2000);
        scrollToDown();
        Thread.sleep(2000);
        logger.debug("Scroll to the end of the page");
        return this;
    }


    public MainPage rastgeleUrunSec() {
        Random rnd = new Random();
        List<WebElement> Products = findElements(BEYMEN_RANDOM_PRODUCT);
        WebElement rndProduct = Products.get(rnd.nextInt(Products.size()));
        rndProduct.click();
        logger.debug("Random product was selected");
        return this;
    }

    public  MainPage rastgeleBedenSec() {
       Random rnd = new Random();
       List<WebElement> Sizes = findElements(BEYMEN_PRODUCT_SIZE);
       WebElement rndSize = Sizes.get(rnd.nextInt(Sizes.size()));
       rndSize.click();
       logger.debug("Random selected size");
       return this;
    }

    public MainPage sepeteEkle() throws InterruptedException {
        productPrice = findElement(BEYMEN_PRODUCT_PRICE).getText();
        findElement(BEYMEN_ADD_CART_BUTTON).click();
        beklemeSuresi(2);
        logger.debug("Product added to cart, price: " + productPrice);
        findElement(BEYMEN_CART_BUTTON).click();
        logger.debug("Go to my cart page");
        return this;
    }

    public MainPage fiyatKarsılastır() {
        String latestPrice = findElement(BEYMEN_PRODUCT_PRICE_IN_CART).getText();
        Assert.assertEquals("Prices don't match.",productPrice,latestPrice);
        logger.debug("The price on the product page is the same as the price in the cart. ");
        return this;
    }

    public MainPage urunArtır() {
        try {
            comboBoxSelector(BEYMEN_AMOUNT_IN_CART,"2");
        }
        catch (NoSuchElementException err) {
            Assert.assertTrue("Cannot continue the test because there is only 1 product left." + err,false);
        }
        logger.debug("The number of products has been increased ");
        String productAmount = findElement(BEYMEN_AMOUNT_IN_CART).getAttribute("value");
        Assert.assertEquals("Product quantity doesn't match ","2", productAmount );
        return this;
    }

    public MainPage urunSil() {
        findElement(BEYMEN_REMOVE_ITEM).click();
        logger.debug("The product has been deleted from the cart.");
        Assert.assertTrue("Product available in cart ",findElement(BEYMEN_CHECK_CART_EMPTY).isDisplayed());
        return this;
    }


}
