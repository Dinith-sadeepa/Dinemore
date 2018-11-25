package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String telephone_no;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String customerName, String customerAddress, String telephone_no) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephone_no = telephone_no;
    }

    public Customer(String customerName, String customerAddress, String telephone_no, List<Orders> orders) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephone_no = telephone_no;
        this.orders = orders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephone_no='" + telephone_no + '\'' +
                ", orders=" + orders +
                '}';
    }
}
