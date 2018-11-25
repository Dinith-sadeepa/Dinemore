package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean deleteCustomer(int id) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public List<CustomerDTO> getAllCustomer() throws Exception;
}
