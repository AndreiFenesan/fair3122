package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testGetterTable() {
        Payment payment = new Payment(1, PaymentType.Card, 100D);
        assertEquals(1, payment.getTableNumber());
    }

    @Test
    void testGetterType() {
        Payment payment = new Payment(1, PaymentType.Card, 100D);
        assertEquals(PaymentType.Card, payment.getType());
    }
}