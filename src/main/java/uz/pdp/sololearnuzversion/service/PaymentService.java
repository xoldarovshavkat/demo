package uz.pdp.sololearnuzversion.service;

import uz.pdp.sololearnuzversion.entity.Payment;
import uz.pdp.sololearnuzversion.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Create
    public Payment createPayment(Payment payment) {
        payment.setCreatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    // Read all
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Read by ID
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found!"));
    }

    // Read by Order ID
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    // Update
    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = getPaymentById(id);
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setStatus(updatedPayment.getStatus());
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setTransactionId(updatedPayment.getTransactionId());
        return paymentRepository.save(existingPayment);
    }

    // Delete
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
