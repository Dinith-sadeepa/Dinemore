package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.OperatorBO;
import lk.ijse.dinemore.dto.OperatorDTO;
import lk.ijse.dinemore.service.custom.OperatorService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OperatorServiceImpl extends UnicastRemoteObject implements OperatorService {

    private OperatorBO operatorBO;
    public OperatorServiceImpl() throws RemoteException {
        operatorBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.OPERATOR);
    }

    @Override
    public boolean addOperator(OperatorDTO operatorDTO) throws Exception {
        return operatorBO.addOperator(operatorDTO);
    }

    @Override
    public boolean deleteOperator(int id) throws Exception {
        return operatorBO.deleteOperator(id);
    }

    @Override
    public boolean updateOperator(OperatorDTO operatorDTO) throws Exception {
        return operatorBO.updateOperator(operatorDTO);
    }

    @Override
    public List<OperatorDTO> getAllOperator() throws Exception {
        return operatorBO.getAllOperator();
    }
}
