package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class DeliveryBoyDTO implements Serializable {
    private int deliveryBoyId;
    private String deliveryBoyName;
    private String deliveryBoyNIC;
    private String deliveryBoyAddress;
    private String deliveryBoyUserName;
    private String deliveryBoyPassword;

    public DeliveryBoyDTO() {
    }

    public DeliveryBoyDTO(int deliveryBoyId, String deliveryBoyName, String deliveryBoyNIC, String deliveryBoyAddress, String deliveryBoyUserName, String deliveryBoyPassword) {
        this.deliveryBoyId = deliveryBoyId;
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNIC = deliveryBoyNIC;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyUserName = deliveryBoyUserName;
        this.deliveryBoyPassword = deliveryBoyPassword;
    }

    public DeliveryBoyDTO(String deliveryBoyName, String deliveryBoyNIC, String deliveryBoyAddress, String deliveryBoyUserName, String deliveryBoyPassword) {
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNIC = deliveryBoyNIC;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyUserName = deliveryBoyUserName;
        this.deliveryBoyPassword = deliveryBoyPassword;
    }

    public int getDeliveryBoyId() {
        return deliveryBoyId;
    }

    public void setDeliveryBoyId(int deliveryBoyId) {
        this.deliveryBoyId = deliveryBoyId;
    }

    public String getDeliveryBoyName() {
        return deliveryBoyName;
    }

    public void setDeliveryBoyName(String deliveryBoyName) {
        this.deliveryBoyName = deliveryBoyName;
    }

    public String getDeliveryBoyNIC() {
        return deliveryBoyNIC;
    }

    public void setDeliveryBoyNIC(String deliveryBoyNIC) {
        this.deliveryBoyNIC = deliveryBoyNIC;
    }

    public String getDeliveryBoyAddress() {
        return deliveryBoyAddress;
    }

    public void setDeliveryBoyAddress(String deliveryBoyAddress) {
        this.deliveryBoyAddress = deliveryBoyAddress;
    }

    public String getDeliveryBoyUserName() {
        return deliveryBoyUserName;
    }

    public void setDeliveryBoyUserName(String deliveryBoyUserName) {
        this.deliveryBoyUserName = deliveryBoyUserName;
    }

    public String getDeliveryBoyPassword() {
        return deliveryBoyPassword;
    }

    public void setDeliveryBoyPassword(String deliveryBoyPassword) {
        this.deliveryBoyPassword = deliveryBoyPassword;
    }

    @Override
    public String toString() {
        return "DeliveryBoyDTO{" +
                "deliveryBoyId=" + deliveryBoyId +
                ", deliveryBoyName='" + deliveryBoyName + '\'' +
                ", deliveryBoyNIC='" + deliveryBoyNIC + '\'' +
                ", deliveryBoyAddress='" + deliveryBoyAddress + '\'' +
                ", deliveryBoyUserName='" + deliveryBoyUserName + '\'' +
                ", deliveryBoyPassword='" + deliveryBoyPassword + '\'' +
                '}';
    }
}

