package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OperatorDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface OperatorService extends SuperService {

    public boolean addOperator(OperatorDTO operatorDTO) throws Exception;

    public boolean deleteOperator(int id) throws Exception;

    public boolean updateOperator(OperatorDTO operatorDTO) throws Exception;

    public List<OperatorDTO> getAllOperator() throws Exception;

}
