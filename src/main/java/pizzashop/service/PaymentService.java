package pizzashop.service;

import pizzashop.exceptions.InvalidPaymentException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private final PaymentRepository payRepo;

    public PaymentService(PaymentRepository payRepo) {
        this.payRepo = payRepo;
    }


    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) {
        Payment payment = new Payment(table, type, amount);
        validatePayment(payment);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> payments = getPayments();
        if ((payments == null) || (payments.isEmpty())) return total;
        for (Payment payment : payments) {
            if (payment.getType().equals(type))
                total += payment.getAmount();
        }
        return total;
    }

    private void validatePayment(Payment payment) {
        String errMessage = "";
        if (payment.getTableNumber() < 0 || payment.getTableNumber() > 20) {
            errMessage += "Invalid table\n";
        }
        if (payment.getAmount() < 0) {
            errMessage += "Invalid amount";
        }
        if (!errMessage.isEmpty()) {
            throw new InvalidPaymentException(errMessage);
        }
    }
}
