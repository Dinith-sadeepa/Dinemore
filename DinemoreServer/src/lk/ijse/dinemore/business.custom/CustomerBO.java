package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean deleteCustomer(int id) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public List<CustomerDTO> getAllCustomer() throws Exception;
}
