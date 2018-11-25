package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.OperatorDTO;

import java.util.List;

public interface OperatorBO extends SuperBO {
    public boolean addOperator(OperatorDTO operatorDTO) throws Exception;

    public boolean deleteOperator(int id) throws Exception;

    public boolean updateOperator(OperatorDTO operatorDTO) throws Exception;

    public List<OperatorDTO> getAllOperator() throws Exception;
}
