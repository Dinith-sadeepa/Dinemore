package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.Date;

public class DeliveryDTO implements Serializable {
    private int deliveryId;
    private Date startTime;
    private Date endTime;

    private OrderDetailDTO orderDetail;
    private DeliveryBoyDTO deliveryBoy;

    public DeliveryDTO() {
    }

    public DeliveryDTO(int deliveryId, Date startTime, Date endTime, OrderDetailDTO orderDetail, DeliveryBoyDTO deliveryBoy) {
        this.deliveryId = deliveryId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderDetail = orderDetail;
        this.deliveryBoy = deliveryBoy;
    }

    public DeliveryDTO(Date startTime, Date endTime, OrderDetailDTO orderDetail, DeliveryBoyDTO deliveryBoy) {
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

    public OrderDetailDTO getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailDTO orderDetail) {
        this.orderDetail = orderDetail;
    }

    public DeliveryBoyDTO getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(DeliveryBoyDTO deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "deliveryId=" + deliveryId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderDetail=" + orderDetail +
                ", deliveryBoy=" + deliveryBoy +
                '}';
    }
}
