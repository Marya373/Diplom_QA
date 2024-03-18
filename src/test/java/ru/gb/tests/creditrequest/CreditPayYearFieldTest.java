package ru.gb.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.pages.MainPage;
import ru.gb.pages.PaymentPage;
import ru.gb.tests.TestBaseUI;

import static ru.gb.data.Data.*;

public class CreditPayYearFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldFailurePaymentIfEmptyYear() {
        val cardData = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearOneSym() {
        val cardData = getInvalidYearIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearBeforeCurrentYear() {
        val cardData = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldExpiredDatePassNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearZero() {
        val cardData = getInvalidYearIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearInTheFarFuture() {
        val cardData = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }
}
