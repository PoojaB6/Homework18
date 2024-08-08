package com.tsw.pageobjecttemplate.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.tsw.pageobjecttemplate.pages.LoginPage;
import com.tsw.pageobjecttemplate.pages.OrderPage;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPageTest {
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://fe-delivery.tallinn-learning.ee/");
    }

    @Test
    public void loginTestAndCheckErrorMessage() {
        loginPage.loginField.setValue("Jackson");
        loginPage.passwordField.setValue("123456789");
        loginPage.signInButton.click();
        loginPage.loginErrorMessage.shouldBe(visible);
      loginPage.popupCloseButton.shouldBe(visible);
    }
    @Test
    public void successfulLogin() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.orderButton.shouldBe(visible);
        orderPage.statusButton.shouldBe(visible);
    }
//Homework18

    @Test
    public void createNewOrder() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.inputUsername.setValue("Jackson");
        orderPage.inputPhone.setValue("243749490");
        orderPage.inputComment.setValue("Welcome");
        orderPage.getOrderButton.click();
        Selenide.sleep(4000);
    }
    @Test
    public void statusButtonForCheckTrackingCode() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.statusButton.click();
        orderPage.inputTrackingCode.setValue("6789");
        orderPage.trackingButton.click();
        Selenide.sleep(2000);
    }
    @Test
    public void statusButtonForCheckInvalidTrackingCode() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.statusButton.click();
        orderPage.inputTrackingCode.setValue("none");
        orderPage.trackingButton.click();
        Selenide.sleep(2000);
    }

    @Test
    public void logoutButtonCheckInOrderPage() {
        OrderPage orderPage = loginPage.performLoginAndReturnOrderPage();
        orderPage.logoutButton.click();
        loginPage.loginField.shouldBe(visible);
        loginPage.passwordField.shouldBe(visible);
        loginPage.signInButton.shouldBe(visible);
        Selenide.sleep(5000);
    }
}
