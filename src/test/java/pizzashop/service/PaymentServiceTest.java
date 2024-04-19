package pizzashop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentService = new PaymentService(paymentRepository);
    }

    @RepeatedTest(2)
    void addPayment() {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(10, PaymentType.Cash, 198D);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }

    @Test
    @Disabled
    void addPaymentIgnore() {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(10, PaymentType.Cash, 198D);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }

    @ParameterizedTest
    @CsvSource(value = {"-5,178", "-3,-180", "7,-200"}, delimiter = ',')
    void shouldThrowExceptionAddPaymentInvalidTable(int table, double amount) {
        assertThrows(Exception.class,
                () -> paymentService.addPayment(table, PaymentType.Cash, amount));
    }

    //---------- BVA

    @ParameterizedTest
    @CsvSource(value = {"1,120", "19,120", "20,122", "10,1"}, delimiter = ',')
    @Order(1)
    void addPaymentSuccess(int table, double amount) {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(table, PaymentType.Cash, amount);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }

    @Test
    void addPaymentMaxAmount() {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(10, PaymentType.Cash, Double.MAX_VALUE - 1);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }

    @Test
    void addPaymentMaxAmount2() {
        int initialSize = paymentService.getPayments().size();
        paymentService.addPayment(10, PaymentType.Cash, Double.MAX_VALUE);

        assertEquals(initialSize + 1, paymentService.getPayments().size());
    }


    @Test
    void getTotalAmountCashNoPayments() {
        paymentRepository.clear();

        double amount = paymentService.getTotalAmount2(PaymentType.Cash);

        assertEquals(0, amount);
    }

    @Test
    void getTotalAmountCardNoPayments() {
        paymentRepository.clear();

        double amount = paymentService.getTotalAmount2(PaymentType.Card);

        assertEquals(0, amount);
    }

    @Test
    void getTotalAmountCardPaymentsCardExists() {
        paymentRepository.clear();
        paymentService.addPayment(1,PaymentType.Card,100d);

        double amount = paymentService.getTotalAmount2(PaymentType.Card);

        assertEquals(100d, amount);
    }

    @Test
    void getTotalAmountCashPaymentsCashExists() {
        paymentRepository.clear();
        paymentService.addPayment(1,PaymentType.Cash,100d);

        double amount = paymentService.getTotalAmount2(PaymentType.Cash);

        assertEquals(100d, amount);
    }

    @Test
    void getTotalAmountCashPaymentsCardExists() {
        paymentRepository.clear();
        paymentService.addPayment(1,PaymentType.Card,100d);

        double amount = paymentService.getTotalAmount2(PaymentType.Cash);

        assertEquals(0, amount);
    }

    @Test
    void getTotalAmountCardPaymentsCashExists() {
        paymentRepository.clear();
        paymentService.addPayment(1,PaymentType.Cash,100d);

        double amount = paymentService.getTotalAmount2(PaymentType.Card);

        assertEquals(0, amount);
    }

    @Test
    void getTotalAmountCrypto() {
        paymentRepository.clear();
        paymentService.addPayment(1,PaymentType.Card,100d);

        double amount = paymentService.getTotalAmount2(PaymentType.Crypto);

        assertEquals(0, amount);
    }

}