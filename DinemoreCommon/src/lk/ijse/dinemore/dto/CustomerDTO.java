package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO implements Serializable {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String telephone_no;

    private List<OrderDTO> orderDTOS = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName, String customerAddress, String telephone_no) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephone_no = telephone_no;
    }

    public CustomerDTO(String customerName, String customerAddress, String telephone_no) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephone_no = telephone_no;
    }

    public CustomerDTO(String customerName, String customerAddress, String telephone_no, List<OrderDTO> orderDTOS) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.telephone_no = telephone_no;
        this.orderDTOS = orderDTOS;
    }

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephone_no='" + telephone_no + '\'' +
                '}';
    }
}
