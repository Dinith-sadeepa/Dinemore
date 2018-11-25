package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.LogInDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface LogInService extends SuperService {
    public void addLogIn(LogInDTO logInDTO) throws Exception;
    public List<LogInDTO> getAllLogin() throws Exception;
}
