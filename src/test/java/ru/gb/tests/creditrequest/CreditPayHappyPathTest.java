package ru.gb.tests.creditrequest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gb.pages.MainPage;
import ru.gb.pages.PaymentPage;
import ru.gb.tests.TestBaseUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.gb.data.Data.getApprovedCard;
import static ru.gb.data.Data.getDeclinedCard;
import static ru.gb.data.SQL.*;

public class CreditPayHappyPathTest extends TestBaseUI {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldSuccessCreditRequestIfValidApprovedCards() {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();

        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForCreditRequest();
        assertEquals(expectedStatus, actualStatus);

        val bankIdExpected = getBankId();
        val paymentIdActual = getPaymentIdForCreditRequest();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(bankIdExpected, paymentIdActual);
    }

    @Test
    public void shouldFailurePayIfValidDeclinedCards() {
        val cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();

        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForCreditRequest();
        assertEquals(expectedStatus, actualStatus);

        val bankIdExpected = getBankId();
        val paymentIdActual = getPaymentIdForCreditRequest();
        assertNotNull(bankIdExpected);
        assertNotNull(paymentIdActual);
        assertEquals(bankIdExpected, paymentIdActual);
    }
}
