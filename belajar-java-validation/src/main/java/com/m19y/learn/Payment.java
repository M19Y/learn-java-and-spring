package com.m19y.learn;

import com.m19y.learn.group.CreditCardPaymentGroup;
import com.m19y.learn.group.VirtualAccountPaymentGroup;
import com.m19y.learn.payload.EmailErrorPayload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;

public class Payment {

  @NotBlank(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
          message = "order id must not be blank")
  @Size(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
          min = 2, max = 10, message = "{order.id.size}")
  private String orderId; // size message stored in ValidationMessages.properties inside resources

  @NotNull(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
          message = "amount must be not null")
  @Range(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
          min = 10_000L,
          max = 100_000_000,
          message = "{order.amount.range}")
  private Long amount;

  @LuhnCheck(groups = CreditCardPaymentGroup.class,
          message = "Invalid credit card number",
          payload = {EmailErrorPayload.class})
  @NotBlank(groups = CreditCardPaymentGroup.class,
          message = "credit card must not be blank")
  private String creditCard;

  @NotBlank(groups = VirtualAccountPaymentGroup.class,
          message = "virtual account must not be blank")
  private String virtualAccount;

  @NotNull(groups = {CreditCardPaymentGroup.class, VirtualAccountPaymentGroup.class},
          message = "customer must not be null")
  @Valid
  @ConvertGroup(from = CreditCardPaymentGroup.class, to = Default.class)
  @ConvertGroup(from = VirtualAccountPaymentGroup.class, to = Default.class)
  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  @Override
  public String toString() {
    return "Payment{" +
            "orderId='" + orderId + '\'' +
            ", amount=" + amount +
            ", creditCard='" + creditCard + '\'' +
            ", virtualAccount='" + virtualAccount + '\'' +
            ", customer=" + customer +
            '}';
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getVirtualAccount() {
    return virtualAccount;
  }

  public void setVirtualAccount(String virtualAccount) {
    this.virtualAccount = virtualAccount;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(String creditCard) {
    this.creditCard = creditCard;
  }
}
