package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Operator")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operatorId;
    private String operatorName;
    private String operatorNIC;
    private String operatorAddress;
    private String operatorUserName;
    private String operatorPassword;

    @OneToMany(mappedBy = "operator",cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public Operator() {
    }

    public Operator(String operatorName, String operatorNIC, String operatorAddress, String operatorUserName, String operatorPassword) {
        this.operatorName = operatorName;
        this.operatorNIC = operatorNIC;
        this.operatorAddress = operatorAddress;
        this.operatorUserName = operatorUserName;
        this.operatorPassword = operatorPassword;
    }

    public Operator(String operatorName, String operatorNIC, String operatorAddress, String operatorUserName, String operatorPassword, List<Orders> orders) {
        this.operatorName = operatorName;
        this.operatorNIC = operatorNIC;
        this.operatorAddress = operatorAddress;
        this.operatorUserName = operatorUserName;
        this.operatorPassword = operatorPassword;
        this.orders = orders;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorNIC() {
        return operatorNIC;
    }

    public void setOperatorNIC(String operatorNIC) {
        this.operatorNIC = operatorNIC;
    }

    public String getOperatorAddress() {
        return operatorAddress;
    }

    public void setOperatorAddress(String operatorAddress) {
        this.operatorAddress = operatorAddress;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }

    public String getOperatorPassword() {
        return operatorPassword;
    }

    public void setOperatorPassword(String operatorPassword) {
        this.operatorPassword = operatorPassword;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", operatorNIC='" + operatorNIC + '\'' +
                ", operatorAddress='" + operatorAddress + '\'' +
                ", operatorUserName='" + operatorUserName + '\'' +
                ", operatorPassword='" + operatorPassword + '\'' +
                ", orders=" + orders +
                '}';
    }
}
