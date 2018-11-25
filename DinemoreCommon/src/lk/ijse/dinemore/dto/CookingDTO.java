package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.Date;

public class CookingDTO implements Serializable {
    private int cookingId;
    private Date startTime;
    private Date endTime;

    private ChefDTO chefDTO;
    private OrderDetailDTO orderDetailDTO;

    public CookingDTO() {
    }

    public CookingDTO(Date startTime, Date endTime, ChefDTO chefDTO, OrderDetailDTO orderDetailDTO) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.chefDTO = chefDTO;
        this.orderDetailDTO = orderDetailDTO;
    }

    public CookingDTO(int cookingId, Date startTime, Date endTime, ChefDTO chefDTO, OrderDetailDTO orderDetailDTO) {
        this.cookingId = cookingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.chefDTO = chefDTO;
        this.orderDetailDTO = orderDetailDTO;
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

    public ChefDTO getChefDTO() {
        return chefDTO;
    }

    public void setChefDTO(ChefDTO chefDTO) {
        this.chefDTO = chefDTO;
    }

    public OrderDetailDTO getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }

    @Override
    public String toString() {
        return "CookingDTO{" +
                "cookingId=" + cookingId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", chefDTO=" + chefDTO +
                ", orderDetailDTO=" + orderDetailDTO +
                '}';
    }
}
