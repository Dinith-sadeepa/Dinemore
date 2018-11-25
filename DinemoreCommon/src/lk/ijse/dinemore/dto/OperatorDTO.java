package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class OperatorDTO implements Serializable {
    private int operatorId;
    private String operatorName;
    private String operatorNIC;
    private String operatorAddress;
    private String operatorUserName;
    private String operatorPassword;

    public OperatorDTO() {
    }

    public OperatorDTO(int operatorId) {
        this.operatorId = operatorId;
    }

    public OperatorDTO(int operatorId, String operatorName, String operatorNIC, String operatorAddress, String operatorUserName, String operatorPassword) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorNIC = operatorNIC;
        this.operatorAddress = operatorAddress;
        this.operatorUserName = operatorUserName;
        this.operatorPassword = operatorPassword;
    }

    public OperatorDTO(String operatorName, String operatorNIC, String operatorAddress, String operatorUserName, String operatorPassword) {
        this.operatorName = operatorName;
        this.operatorNIC = operatorNIC;
        this.operatorAddress = operatorAddress;
        this.operatorUserName = operatorUserName;
        this.operatorPassword = operatorPassword;
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

    @Override
    public String toString() {
        return "OperatorDTO{" +
                "operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", operatorNIC='" + operatorNIC + '\'' +
                ", operatorAddress='" + operatorAddress + '\'' +
                ", operatorUserName='" + operatorUserName + '\'' +
                ", operatorPassword='" + operatorPassword + '\'' +
                '}';
    }
}
