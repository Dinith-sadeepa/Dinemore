package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @OneToOne
    @JoinColumn(name = "orderDetailId", referencedColumnName = "orderDetailId")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "deliveryBoyId", referencedColumnName = "deliveryBoyId")
    private DeliveryBoy deliveryBoy;

    public Delivery() {
    }

    public Delivery(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Delivery(Date startTime, Date endTime, OrderDetail orderDetail, DeliveryBoy deliveryBoy) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderDetail = orderDetail;
        this.deliveryBoy = deliveryBoy;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public DeliveryBoy getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderDetail=" + orderDetail +
                ", deliveryBoy=" + deliveryBoy +
                '}';
    }
}
