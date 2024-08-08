package com.tsw.pageobjecttemplate.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://fe-delivery.tallinn-learning.ee/

public class OrderPage {
        public SelenideElement statusButton = $x("//*[@data-name='openStatusPopup-button']");
        public SelenideElement orderButton = $x("//*[@data-name='createOrder-button']");
        public SelenideElement inputUsername = $x("//*[@data-name='username-input']");
        public SelenideElement inputPhone = $x("//*[@data-name='phone-input']");
        public SelenideElement inputComment = $x("//*[@data-name='comment-input']");
        public SelenideElement getOrderButton = $x("//*[@data-name='createOrder-button']");
        public SelenideElement inputTrackingCode = $x("//*[@data-name='searchOrder-input']");
        public SelenideElement trackingButton = $x("//*[@data-name='searchOrder-submitButton']");
        public SelenideElement logoutButton = $x("//*[@data-name='logout-button']");
}


