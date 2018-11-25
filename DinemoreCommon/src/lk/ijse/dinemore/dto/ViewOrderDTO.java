package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.Date;

public class ViewOrderDTO implements Serializable {
    private int orderId;
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String cookingStatus;
    private String deliveryStatus;
    private Date orderDate;

    public ViewOrderDTO() {
    }

    public ViewOrderDTO(int orderId, int customerId, String customerName, String customerAddress, String cookingStatus, String deliveryStatus, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.cookingStatus = cookingStatus;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getCookingStatus() {
        return cookingStatus;
    }

    public void setCookingStatus(String cookingStatus) {
        this.cookingStatus = cookingStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "ViewOrderDTO{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", cookingStatus='" + cookingStatus + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
