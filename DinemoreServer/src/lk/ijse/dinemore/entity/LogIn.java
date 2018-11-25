package lk.ijse.dinemore.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LogIn")
public class LogIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logInId;

    private int userId;
    @Temporal(TemporalType.TIME)
    private Date loginTime;

    public LogIn() {
    }

    public LogIn(int userId, Date loginTime) {
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
