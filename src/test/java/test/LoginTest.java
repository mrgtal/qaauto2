package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

public class LoginTest {

    WebDriver webDriver;
    String username = "sst.tau@gmail.com";
    String password = "P@ssword123";

    @BeforeMethod
    public void beforeMethod() {

       webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }

    @Test
    public void testLoginPositive() {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");

    }

    @Test
    public void TestLoginNegative() {

        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");

        LoginPage resultPage = loginPage.loginAsReturnToLoginPage("IncorrectEmail", "IncorrectPassword");

        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(resultPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");

    }

    @Test
    public void TestLogout() {

        LoginPage loginPage = new LoginPage(webDriver);

        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");

        loginPage = mainPage.logOut();

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

    }


}
