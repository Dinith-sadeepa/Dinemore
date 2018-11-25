package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {
    private int orderId;
    private Date orderDate;
    private CustomerDTO customerDTO;
    private List<OrderDetailDTO> orderDetailDTOS;
    private OperatorDTO operatorDTO;

    public OrderDTO() {
    }

    public OrderDTO(Date orderDate, CustomerDTO customerDTO) {
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
    }

    public OrderDTO(int orderId, Date orderDate, CustomerDTO customerDTO, OperatorDTO operatorDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
        this.operatorDTO = operatorDTO;
    }

    public OrderDTO(Date orderDate, CustomerDTO customerDTO, List<OrderDetailDTO> orderDetailDTOS, OperatorDTO operatorDTO) {
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
        this.orderDetailDTOS = orderDetailDTOS;
        this.operatorDTO = operatorDTO;
    }

    public OrderDTO(int orderId, Date orderDate, CustomerDTO customerDTO, List<OrderDetailDTO> orderDetailDTOS, OperatorDTO operatorDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
        this.orderDetailDTOS = orderDetailDTOS;
        this.operatorDTO = operatorDTO;
    }

    public OperatorDTO getOperatorDTO() {
        return operatorDTO;
    }

    public void setOperatorDTO(OperatorDTO operatorDTO) {
        this.operatorDTO = operatorDTO;
    }

    public List<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
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

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerDTO=" + customerDTO +
                '}';
    }
}
