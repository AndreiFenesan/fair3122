package pizzashop.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PaymentRepositoryTest {

    private final PaymentRepository underTest = new PaymentRepository();

    @Test
    void add() {
        int size = underTest.getAll().size();
        Payment p = new Payment(1, PaymentType.Card, 100D);
        underTest.add(p);
        assertEquals(size + 1, underTest.getAll().size());
    }

    @Test
    void getAll() {
        List<Payment> payments = underTest.getAll();
        payments.forEach(p -> assertTrue(p.getAmount() > 0));
    }
}

