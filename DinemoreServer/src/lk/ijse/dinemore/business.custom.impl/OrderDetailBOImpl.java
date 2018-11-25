package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.OrderDetailBO;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.entity.*;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    private SessionFactory sessionFactory;

    public OrderDetailBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addOrderDetail(OrderDetailDTO orderDetail) throws Exception {
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//
//            OrderDetail orderDetail1 = new OrderDetail(orderDetail.getOrderdQty(),orderDetail.getPrice());
//            orderDetail1.setOrders(new Orders(orderDetail.getOrderDTO().getOrderId(),orderDetail.getOrderDTO().getOrderDate(),
//                            new Customer(orderDetail.getOrderDTO().getCustomerDTO().getCustomerName(),orderDetail.getOrderDTO().getCustomerDTO().getCustomerAddress(),orderDetail.getOrderDTO().getCustomerDTO().getTelephone_no()),
//                            new Operator(orderDetail.getOrders().getOperator().getOperatorName(),orderDetail.getOrders().getOperator().getOperatorNIC(),orderDetail.getOrders().getOperator().getOperatorAddress(),orderDetail.getOrders().getOperator().getOperatorUserName(),orderDetail.getOrders().getOperator().getOperatorPassword())),
//                    new Meal(orderDetail.getMeal().getMealId(),orderDetail.getMeal().getMealName(),orderDetail.getMeal().getMealCategory(),orderDetail.getMeal().getMealUnitPrice()));
//            boolean isSaved = (session.save(orderDetail) != null);
//            session.getTransaction().commit();
//            return isSaved;
//        }
        return true;
    }

    @Override
    public boolean deleteOrderDetail(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetail() throws Exception {
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        List<OrderDetail> list;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(OrderDetail.class).list();
            session.getTransaction().commit();
        }
        for (OrderDetail orderDetail :list) {
            orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getOrderDetailId(),orderDetail.getOrderdQty(),orderDetail.getPrice(),
                    new OrderDTO(orderDetail.getOrders().getOrderId(),orderDetail.getOrders().getOrderDate(),
                            new CustomerDTO(orderDetail.getOrders().getCustomer().getCustomerId(),orderDetail.getOrders().getCustomer().getCustomerName(),orderDetail.getOrders().getCustomer().getCustomerAddress(),orderDetail.getOrders().getCustomer().getTelephone_no()),
                            new OperatorDTO(orderDetail.getOrders().getOperator().getOperatorId(),orderDetail.getOrders().getOperator().getOperatorName(),orderDetail.getOrders().getOperator().getOperatorNIC(),orderDetail.getOrders().getOperator().getOperatorAddress(),orderDetail.getOrders().getOperator().getOperatorUserName(),orderDetail.getOrders().getOperator().getOperatorPassword())),
                    new MealDTO(orderDetail.getMeal().getMealId(),orderDetail.getMeal().getMealName(),orderDetail.getMeal().getMealCategory(),orderDetail.getMeal().getMealUnitPrice())));
        }
        return orderDetailDTOS;
    }
}
