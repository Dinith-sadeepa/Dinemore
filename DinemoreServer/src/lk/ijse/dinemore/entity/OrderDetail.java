package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailId;
    private int orderdQty;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
//    @Id
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
//    @Id
    @JoinColumn(name = "mealId",referencedColumnName = "mealId")
    private Meal meal;

//    @ManyToOne
//    @Id
//    @JoinColumn(name = "chefId",referencedColumnName = "chefId")
//    private Chef chef;
//
//    @ManyToOne
//    @Id
//    @JoinColumn(name = "deliveryBoyId",referencedColumnName = "deliveryBoyId")
//    private DeliveryBoy deliveryBoy;


    @OneToOne(mappedBy = "orderDetail" , cascade = CascadeType.ALL)
    private Cooking cooking;

    @OneToOne(mappedBy = "orderDetail",cascade = CascadeType.ALL)
    private Delivery delivery;

    public OrderDetail() {
    }

    public OrderDetail(int orderdQty, double price) {
        this.orderdQty = orderdQty;
        this.price = price;
    }

    public OrderDetail(int orderdQty, double price, Orders orders, Meal meal) {
        this.orderdQty = orderdQty;
        this.price = price;
        this.orders = orders;
        this.meal = meal;
    }

    public OrderDetail(int orderdQty, double price, Orders orders, Meal meal, Cooking cooking) {
        this.orderdQty = orderdQty;
        this.price = price;
        this.orders = orders;
        this.meal = meal;
        this.cooking = cooking;
    }

    public OrderDetail(int orderdQty, double price, Orders orders, Meal meal, Delivery delivery) {
        this.orderdQty = orderdQty;
        this.price = price;
        this.orders = orders;
        this.meal = meal;
        this.delivery = delivery;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Cooking getCooking() {
        return cooking;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderdQty=" + orderdQty +
                ", price=" + price +
                ", orders=" + orders +
                ", meal=" + meal +
                '}';
    }
}
