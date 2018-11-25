package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class ChefDTO implements Serializable {
    private int chefId;
    private String chefName;
    private String chefNIC;
    private String chefAddress;
    private String chefUserName;
    private String chefPassword;

    public ChefDTO() {
    }

    public ChefDTO(int chefId, String chefName, String chefNIC, String chefAddress, String chefUserName, String chefPassword) {
        this.chefId = chefId;
        this.chefName = chefName;
        this.chefNIC = chefNIC;
        this.chefAddress = chefAddress;
        this.chefUserName = chefUserName;
        this.chefPassword = chefPassword;
    }

    public ChefDTO(String chefName, String chefNIC, String chefAddress, String chefUserName, String chefPassword) {
        this.chefName = chefName;
        this.chefNIC = chefNIC;
        this.chefAddress = chefAddress;
        this.chefUserName = chefUserName;
        this.chefPassword = chefPassword;
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
        return "ChefDTO{" +
                "chefId=" + chefId +
                ", chefName='" + chefName + '\'' +
                ", chefNIC='" + chefNIC + '\'' +
                ", chefAddress='" + chefAddress + '\'' +
                ", chefUserName='" + chefUserName + '\'' +
                ", chefPassword='" + chefPassword + '\'' +
                '}';
    }
}
