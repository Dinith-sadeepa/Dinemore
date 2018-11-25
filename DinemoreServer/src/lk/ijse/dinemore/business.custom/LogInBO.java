package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.LogInDTO;

import java.util.List;

public interface LogInBO extends SuperBO {
    public void addLogIn(LogInDTO logInDTO) throws Exception;
    public List<LogInDTO> getAllLogin() throws Exception;
}
