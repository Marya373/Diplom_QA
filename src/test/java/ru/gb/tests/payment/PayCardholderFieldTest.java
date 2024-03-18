package ru.gb.tests.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.pages.MainPage;
import ru.gb.pages.PaymentPage;
import ru.gb.tests.TestBaseUI;

import static ru.gb.data.Data.*;

public class PayCardholderFieldTest extends TestBaseUI {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardholderName() {
        val cardData = getInvalidCardholderNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameOneWord() {
        val cardData = getInvalidCardholderNameIfOneWord();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameThreeWords() {
        val cardData = getInvalidCardholderNameIfThreeWords();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRusSym() {
        val cardData = getInvalidCardholderNameIfRusSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        val cardData = getInvalidCardholderNameIfNumeric();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameWildcard() {
        val cardData = getInvalidCardholderNameIfWildcard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }
}
