package pizzashop.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pizzashop.model.PaymentType;
import pizzashop.service.PaymentService;

import java.util.Optional;

public class PaymentAlert implements PaymentOperation {
    private final PaymentService paymentService;

    public PaymentAlert(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public void cardPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying by card...");
        System.out.println("Please insert your card!");
        System.out.println("--------------------------");
    }

    @Override
    public void cashPayment() {
        System.out.println("--------------------------");
        System.out.println("Paying cash...");
        System.out.println("Please show the cash...!");
        System.out.println("--------------------------");
    }

    @Override
    public void cancelPayment() {
        System.out.println("--------------------------");
        System.out.println("Payment choice needed...");
        System.out.println("--------------------------");
    }

    public void showPaymentAlert(int tableNumber, double totalAmount) {
        Alert paymentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment for Table " + tableNumber);
        paymentAlert.setHeaderText("Total amount: " + totalAmount);
        paymentAlert.setContentText("Please choose payment option");
        ButtonType cardPayment = new ButtonType("Pay by Card");
        ButtonType cashPayment = new ButtonType("Pay Cash");
        ButtonType cancel = new ButtonType("Cancel");
        paymentAlert.getButtonTypes().setAll(cardPayment, cashPayment, cancel);
        Optional<ButtonType> result = paymentAlert.showAndWait();
        if (result.isPresent() && result.get() == cardPayment) {
            cardPayment();
            paymentService.addPayment(tableNumber, PaymentType.Card, totalAmount);
        } else if (result.isPresent() && result.get() == cashPayment) {
            cashPayment();
            paymentService.addPayment(tableNumber, PaymentType.Cash, totalAmount);
        } else if (result.isPresent() && result.get() == cancel) {
            cancelPayment();
        } else {
            cancelPayment();
        }
    }
}