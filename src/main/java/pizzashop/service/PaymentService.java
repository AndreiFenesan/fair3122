package pizzashop.service;

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
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> payments = getPayments();
        if ((payments == null) || (payments.size() == 0)) return total;
        for (Payment payment : payments) {
            if (payment.getType().equals(type))
                total += payment.getAmount();
        }
        return total;
    }
}
