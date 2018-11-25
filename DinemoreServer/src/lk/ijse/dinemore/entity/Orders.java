package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operatorId",referencedColumnName = "operatorId")
    private Operator operator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private Customer customer;

//    @OneToOne(mappedBy = "orders",cascade = CascadeType.ALL)
//    private Payment payment;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Orders() {
    }

//    public Orders(Date orderDate, Operator operator, Customer customer, Payment payment) {
//        this.orderDate = orderDate;
//        this.operator = operator;
//        this.customer = customer;
//        this.payment = payment;
//    }


    public Orders(Date orderDate, Operator operator, Customer customer, List<OrderDetail> orderDetails) {
        this.orderDate = orderDate;
        this.operator = operator;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public Orders(Date orderDate, Operator operator, Customer customer) {
        this.orderDate = orderDate;
        this.operator = operator;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public Payment getPayment() {
//        return payment;
//    }
//
//    public void setPayment(Payment payment) {
//        this.payment = payment;
//    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", operator=" + operator +
                ", customer=" + customer +
//                ", payment=" + payment +
                '}';
    }
}
