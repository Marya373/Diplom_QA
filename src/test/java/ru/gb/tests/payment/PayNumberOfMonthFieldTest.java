package ru.gb.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.pages.MainPage;
import ru.gb.pages.PaymentPage;
import ru.gb.tests.TestBaseUI;

import static ru.gb.data.Data.*;

public class PayNumberOfMonthFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyNumberOfMonth() {
        val cardData = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfOneSym() {
        val cardData = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfMore12() {
        val cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthZero() {
        val cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }
}
