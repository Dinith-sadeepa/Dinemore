package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.LogInBO;
import lk.ijse.dinemore.dto.LogInDTO;
import lk.ijse.dinemore.service.custom.LogInService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LogInServiceImpl extends UnicastRemoteObject implements LogInService {
    private LogInBO logInBO;
    public LogInServiceImpl() throws RemoteException {
        logInBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.LOG_IN);
    }

    @Override
    public void addLogIn(LogInDTO logInDTO) throws Exception {
        logInBO.addLogIn(logInDTO);
    }

    @Override
    public List<LogInDTO> getAllLogin() throws Exception {
        return logInBO.getAllLogin();
    }
}
