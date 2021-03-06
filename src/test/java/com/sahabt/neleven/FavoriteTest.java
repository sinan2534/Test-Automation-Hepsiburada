package com.sahabt.neleven;

import com.sahabt.neleven.page.HomePage;
import com.sahabt.neleven.page.SearchPage;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FavoriteTest {

  WebDriver webDriver;

  @Before
  public void beforeTest() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    webDriver.manage().window().fullscreen();
    webDriver.get("https://www.hepsiburada.com/");
  }

  @Test
  public void test() {
    SearchPage searchPage = new HomePage(webDriver)
        .openLoginPage()
        .login("sinanyzc2534@gmail.com", "karadayi601")
        .searchProduct("Iphone")
        .checkSearchProductName("Iphone")
        .openPage(2);
    String selectProductName = searchPage.getProductNameByIndex(3);
    System.out.println(String.format("Favorilere eklenen urun " + selectProductName));
    searchPage.addToFavorite(3)
        .openAccountPage()
        .openWishList()
        .openMyFavorites()
        .deleteFavoriteByName(selectProductName)
        .checkFavoriteByName(selectProductName)
        .logout();
  }


  @After
  public void afterTest() {
    webDriver.quit();
  }


}