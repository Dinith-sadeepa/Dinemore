package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cooking")
public class Cooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cookingId;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @OneToOne
    @JoinColumn(name = "orderDetailId",referencedColumnName = "orderDetailId")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "chefId",referencedColumnName = "chefId")
    private Chef chef;

    public Cooking() {
    }

    public Cooking(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Cooking(Date startTime, Date endTime, OrderDetail orderDetail, Chef chef) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderDetail = orderDetail;
        this.chef = chef;
    }

    public int getCookingId() {
        return cookingId;
    }

    public void setCookingId(int cookingId) {
        this.cookingId = cookingId;
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

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    @Override
    public String toString() {
        return "Cooking{" +
                "cookingId=" + cookingId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderDetail=" + orderDetail +
                ", chef=" + chef +
                '}';
    }
}
