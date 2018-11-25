package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private int orderDetailId;
    private int orderdQty;
    private double price;
    private OrderDTO orderDTO;
    private MealDTO mealDTO;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailId, int orderdQty, double price, OrderDTO orderDTO, MealDTO mealDTO) {
        this.orderDetailId = orderDetailId;
        this.orderdQty = orderdQty;
        this.price = price;
        this.orderDTO = orderDTO;
        this.mealDTO = mealDTO;
    }

    public OrderDetailDTO(int orderdQty, double price, OrderDTO orderDTO, MealDTO mealDTO) {
        this.orderdQty = orderdQty;
        this.price = price;
        this.orderDTO = orderDTO;
        this.mealDTO = mealDTO;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderdQty() {
        return orderdQty;
    }

    public void setOrderdQty(int orderdQty) {
        this.orderdQty = orderdQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public MealDTO getMealDTO() {
        return mealDTO;
    }

    public void setMealDTO(MealDTO mealDTO) {
        this.mealDTO = mealDTO;
    }
}
