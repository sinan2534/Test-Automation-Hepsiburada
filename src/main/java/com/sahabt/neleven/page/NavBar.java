package com.sahabt.neleven.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public abstract class NavBar<T> extends BasePage {

    public NavBar(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        click(findByClickable(By.cssSelector(".register")));
        click(findByClickable(By.cssSelector(".usersProcess")));

        return new LoginPage(webDriver);
    }

    public T checkUsernameIsVisible() {


        boolean existElement = webDriver.findElements( By.xpath("//*[@id=\"root\"]/div/div[4]/div/div[1]/div/div/div[1]/span") ).size() != 0;

        System.out.println(existElement);

        //    Actions action = new Actions(webDriver);
        //    WebElement element = webDriver.findElement(By.xpath("//*[@id=\"myAccount\"]/a[1]"));//    action.moveToElement(element).build().perform();
        //    element.click();
        //    webDriver.findElement(By.xpath("//div[@class='usersProsess']/a[@class='login user-name' and text()='sinan yazıcı']"));
        // click(findByClickable(By.cssSelector(".myAccount")));

        //    WebElement element = webDriver.findElement(By.xpath("//div[@id='MyAccount']"));
        //    Assert.assertTrue(element.getText().contains("sinan yazıcı"));
        //
        //    try {
        //      webDriver.findElement(By.xpath("//div[@class='usersProsess']/a[@class='login user-name' and text()='sinan yazıcı']"));
        //      return getT();
        //    } catch (NoSuchElementException e) {
        //      Assert.fail(e.getMessage());
        //      return null;
        //    }
        return getT();

    }

    public SearchPage searchProduct(String productName) {
        sendKeys(findByVisible(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[1]/header/div[2]/div[2]/div/input")), productName);
        click(findByClickable(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[1]/header/div[2]/div[2]/div/button")));
        return new SearchPage(webDriver);
    }

    public AccountPage openAccountPage() {
        click(findByClickable(By.cssSelector(".myAccount>.menuTitle")));
        Assert.assertTrue(getText(findByVisible(By.cssSelector("#breadCrumb>ul>li:nth-of-type(2)")))
                .contains("Hesabım"));
        return new AccountPage(webDriver);
    }

    public LoginPage logout() {
        scrollToElement(findByVisible(By.cssSelector(".myAccount>.icon-view-account user-login")));
        click(findByClickable(By.cssSelector(".logout")));
        Assert.assertNotNull(find(By.id("email")));
        return new LoginPage(webDriver);
    }

    protected abstract T getT();
}