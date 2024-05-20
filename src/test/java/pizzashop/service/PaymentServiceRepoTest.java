package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class PaymentServiceRepoTest {
    private PaymentRepository paymentRepository;

    private PaymentService underTest;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        underTest = new PaymentService(paymentRepository);
    }

    @Test
    void addPayment() {
        int initialSize = underTest.getPayments().size();
        underTest.addPayment(1, PaymentType.Cash, 100D);

        assertEquals(initialSize + 1, underTest.getPayments().size());
    }
}
