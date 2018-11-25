package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DeliveryBoy")
public class DeliveryBoy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryBoyId;
    private String deliveryBoyName;
    private String deliveryBoyNIC;
    private String deliveryBoyAddress;
    private String deliveryBoyUserName;
    private String deliveryBoyPassword;

    @OneToMany(mappedBy = "deliveryBoy",cascade = CascadeType.ALL)
    private List<Delivery> delivery;

    public DeliveryBoy() {
    }

    public DeliveryBoy(String deliveryBoyName, String deliveryBoyNIC, String deliveryBoyAddress, String deliveryBoyUserName, String deliveryBoyPassword) {
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNIC = deliveryBoyNIC;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyUserName = deliveryBoyUserName;
        this.deliveryBoyPassword = deliveryBoyPassword;
    }

    public DeliveryBoy(String deliveryBoyName, String deliveryBoyNIC, String deliveryBoyAddress, String deliveryBoyUserName, String deliveryBoyPassword, List<Delivery> delivery) {
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNIC = deliveryBoyNIC;
        this.deliveryBoyAddress = deliveryBoyAddress;
        this.deliveryBoyUserName = deliveryBoyUserName;
        this.deliveryBoyPassword = deliveryBoyPassword;
        this.delivery = delivery;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
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
        return "DeliveryBoy{" +
                "deliveryBoyId=" + deliveryBoyId +
                ", deliveryBoyName='" + deliveryBoyName + '\'' +
                ", deliveryBoyNIC='" + deliveryBoyNIC + '\'' +
                ", deliveryBoyAddress='" + deliveryBoyAddress + '\'' +
                ", deliveryBoyUserName='" + deliveryBoyUserName + '\'' +
                ", deliveryBoyPassword='" + deliveryBoyPassword + '\'' +
                '}';
    }
}
