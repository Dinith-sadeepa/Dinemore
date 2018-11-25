package lk.ijse.dinemore.entity;

import javax.persistence.*;

//@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private String creditCardNum;
    private String expireDate;
    private String paymentStatus;

    @OneToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Orders orders;

    public Payment() {
    }

    public Payment(String creditCardNum, String expireDate, String paymentStatus, Orders orders) {
        this.creditCardNum = creditCardNum;
        this.expireDate = expireDate;
        this.paymentStatus = paymentStatus;
        this.orders = orders;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", creditCardNum='" + creditCardNum + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", orders=" + orders +
                '}';
    }
}
