package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.util.Date;

public class LogInDTO implements Serializable {
    private int logInId;
    private int userId;
    private Date loginTime;

    public LogInDTO() {
    }

    public LogInDTO(int logInId, int userId, Date loginTime) {
        this.logInId = logInId;
        this.userId = userId;
        this.loginTime = loginTime;
    }

    public LogInDTO(int userId, Date loginTime) {
        this.userId = userId;
        this.loginTime = loginTime;
    }

    public int getLogInId() {
        return logInId;
    }

    public void setLogInId(int logInId) {
        this.logInId = logInId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "logInId=" + logInId +
                ", userId=" + userId +
                ", loginTime=" + loginTime +
                '}';
    }
}
