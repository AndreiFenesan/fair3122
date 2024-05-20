package pizzashop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceMockTest {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService underTest;

    @Test
    void addPayment() {
        underTest.addPayment(1, PaymentType.Cash, 100D);
        verify(paymentRepository, times(1)).add(new Payment(1, PaymentType.Cash, 100D));
    }

    @Test
    void getTotalAmount() {
        when(paymentRepository.getAll()).thenReturn(List.of(
                new Payment(1, PaymentType.Card, 100D),
                new Payment(2, PaymentType.Cash, 100D)
        ));

        double total = underTest.getTotalAmount(PaymentType.Card);
        assertEquals(100D, total);
    }
}
