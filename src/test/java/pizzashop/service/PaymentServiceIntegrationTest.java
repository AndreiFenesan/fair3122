package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceIntegrationTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentService = new PaymentService(paymentRepository);
    }

    @Test
    void addPaymentSuccess() {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(1, PaymentType.Cash, 100D);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }
}
