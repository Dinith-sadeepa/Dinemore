package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chefId;
    private String chefName;
    private String chefNIC;
    private String chefAddress;
    private String chefUserName;
    private String chefPassword;

//    @OneToMany(mappedBy = "chef",cascade = CascadeType.ALL)
//    private List<OrderDetail> orderDetails = new ArrayList<>();
    @OneToMany(mappedBy = "chef",cascade = CascadeType.ALL)
    private List<Cooking> cooking;

    public Chef() {
    }

    public Chef(String chefName, String chefNIC, String chefAddress, String chefUserName, String chefPassword) {
        this.chefName = chefName;
        this.chefNIC = chefNIC;
        this.chefAddress = chefAddress;
        this.chefUserName = chefUserName;
        this.chefPassword = chefPassword;
    }

//    public Chef(String chefName, String chefNIC, String chefAddress, String chefUserName, String chefPassword, List<OrderDetail> orderDetails) {
//        this.chefName = chefName;
//        this.chefNIC = chefNIC;
//        this.chefAddress = chefAddress;
//        this.chefUserName = chefUserName;
//        this.chefPassword = chefPassword;
//        this.orderDetails = orderDetails;
//    }


    public Chef(String chefName, String chefNIC, String chefAddress, String chefUserName, String chefPassword, List<Cooking> cooking) {
        this.chefName = chefName;
        this.chefNIC = chefNIC;
        this.chefAddress = chefAddress;
        this.chefUserName = chefUserName;
        this.chefPassword = chefPassword;
        this.cooking = cooking;
    }

    public List<Cooking> getCooking() {
        return cooking;
    }

    public void setCooking(List<Cooking> cooking) {
        this.cooking = cooking;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public String getChefNIC() {
        return chefNIC;
    }

    public void setChefNIC(String chefNIC) {
        this.chefNIC = chefNIC;
    }

    public String getChefAddress() {
        return chefAddress;
    }

    public void setChefAddress(String chefAddress) {
        this.chefAddress = chefAddress;
    }

    public String getChefUserName() {
        return chefUserName;
    }

    public void setChefUserName(String chefUserName) {
        this.chefUserName = chefUserName;
    }

    public String getChefPassword() {
        return chefPassword;
    }

    public void setChefPassword(String chefPassword) {
        this.chefPassword = chefPassword;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "chefId=" + chefId +
                ", chefName='" + chefName + '\'' +
                ", chefNIC='" + chefNIC + '\'' +
                ", chefAddress='" + chefAddress + '\'' +
                ", chefUserName='" + chefUserName + '\'' +
                ", chefPassword='" + chefPassword + '\'' +
                '}';
    }
}
