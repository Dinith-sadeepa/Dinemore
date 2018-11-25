package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.CustomerBO;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.entity.Customer;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private SessionFactory sessionFactory;

    public CustomerBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            boolean isAdded = (session.save(new Customer(customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getTelephone_no())) != null);
            session.getTransaction().commit();
            return isAdded;
        }
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
        List<Customer> list;
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Customer.class).list();
            session.getTransaction().commit();
        }
        for (Customer customer : list) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getTelephone_no()));
        }
        return customerDTOS;
    }
}
