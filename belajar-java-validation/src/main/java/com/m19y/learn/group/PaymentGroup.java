package com.m19y.learn.group;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

// akan di eksekusi sesuai urutan
// apabila urutan 1 error maka urutan ke 2 tidak akan di eksekusi
// apabila urutan 2 error maka urutan ke 3 tidak akan di eksekusi
@GroupSequence(value = {
        Default.class,
        CreditCardPaymentGroup.class,
        VirtualAccountPaymentGroup.class
})
public interface PaymentGroup {
}
