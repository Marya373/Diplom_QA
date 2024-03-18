package ru.gb.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.pages.MainPage;
import ru.gb.pages.PaymentPage;
import ru.gb.tests.TestBaseUI;

import static ru.gb.data.Data.*;

public class PayCardNumberFieldTest extends TestBaseUI {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        val cardData = getInvalidCardNumberIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        val cardData = getInvalidCardNumberIfLess16Sym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        val cardData = getInvalidCardNumberIfOutOfBase();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();
    }
}
