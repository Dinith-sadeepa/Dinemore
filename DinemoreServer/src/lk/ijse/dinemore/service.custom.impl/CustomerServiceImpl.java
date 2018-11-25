package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.CustomerBO;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.service.custom.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {
    private CustomerBO customerBO;
    public CustomerServiceImpl() throws RemoteException {
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        return customerBO.addCustomer(customerDTO);
    }

    @Override
    public boolean deleteCustomer(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        return customerBO.getAllCustomer();
    }
}
