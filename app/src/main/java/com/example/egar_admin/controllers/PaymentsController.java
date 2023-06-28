package com.example.egar_admin.controllers;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import java.util.Arrays;

//@RestController
//@RequestMapping("/payments")
public class PaymentsController {

    private final APIContext apiContext;

    public PaymentsController() {
        apiContext = new APIContext("YOUR_CLIENT_ID", "YOUR_CLIENT_SECRET", "sandbox");
    }

//    @PostMapping("/create")
    public String createPayment() {
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("10.00");

        Transaction transaction = new Transaction();
        transaction.setDescription("Payment description");
        transaction.setAmount(amount);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://example.com/return");
        redirectUrls.setCancelUrl("http://example.com/cancel");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(Arrays.asList(transaction));

        try {
            Payment createdPayment = payment.create(apiContext);
            String approvalUrl = createdPayment.getLinks().stream()
                    .filter(link -> link.getRel().equals("approval_url"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No approval_url found"))
                    .getHref();

            return approvalUrl;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @GetMapping("/execute")
//    public String executePayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = new Payment();
//            payment.setId(paymentId);
//
//            PaymentExecution paymentExecution = new PaymentExecution();
//            paymentExecution.setPayerId(payerId);
//
//            Payment executedPayment = payment.execute(apiContext, paymentExecution);
//            String paymentStatus = executedPayment.getState();
//
//            return paymentStatus;
//        } catch (PayPalRESTException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}

