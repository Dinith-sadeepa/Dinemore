package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.ChefQueueBO;
import lk.ijse.dinemore.business.custom.CustomerBO;
import lk.ijse.dinemore.business.custom.OrderDetailBO;
import lk.ijse.dinemore.business.custom.OrdersBO;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.entity.*;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private CustomerBO customerBO;
    private OrderDetailBO orderDetailBO;
    private ChefQueueBO chefQueueBO;

    public OrdersBOImpl(){
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
        orderDetailBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ORDER_DETAIL);
        chefQueueBO = ChefQueueBOImpl.getInstance();
    }

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            Customer customer;
            if(orderDTO.getCustomerDTO().getCustomerId() == 0) {
                customer = new Customer();
                customer.setCustomerName(orderDTO.getCustomerDTO().getCustomerName());
                customer.setCustomerAddress(orderDTO.getCustomerDTO().getCustomerAddress());
                customer.setTelephone_no(orderDTO.getCustomerDTO().getTelephone_no());
            }else{
                customer = session.get(Customer.class,orderDTO.getCustomerDTO().getCustomerId());
            }
//            customerBO.addCustomer(customer);
            Operator operator = session.get(Operator.class, orderDTO.getOperatorDTO().getOperatorId());

            Orders orders = new Orders();
            orders.setOrderDate(orderDTO.getOrderDate());
            orders.setCustomer(customer);
            orders.setOperator(operator);
            session.save(orders);


            List<OrderDetail> orderDetails = new ArrayList<>();
            System.out.println(orderDTO.getOrderDetailDTOS());
            for (OrderDetailDTO orderDetailDTO :orderDTO.getOrderDetailDTOS()) {
                MealDTO mealDTO = orderDetailDTO.getMealDTO();
                Meal meal = new Meal();
                meal.setMealId(mealDTO.getMealId());
                meal.setMealName(mealDTO.getMealName());
                meal.setMealCategory(mealDTO.getMealCategory());
                meal.setMealUnitPrice(mealDTO.getMealUnitPrice());


                OrderDetail orderDetail =  new OrderDetail();
                orderDetail.setOrderdQty(orderDetailDTO.getOrderdQty());
                orderDetail.setPrice(orderDetailDTO.getPrice());
                orderDetail.setMeal(meal);
                orderDetail.setOrders(orders);
//                orderDetails.add(orderDetail);
//                orderDetailBO.addOrderDetail(orderDetail);
                session.save(orderDetail);
                chefQueueBO.addFirstChefQueue(new OrderDetailDTO(orderDetail.getOrderdQty(),orderDetail.getPrice(),
                        new OrderDTO(orderDetail.getOrders().getOrderId(),orderDetail.getOrders().getOrderDate(),
                                new CustomerDTO(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getTelephone_no()),
                                new OperatorDTO(operator.getOperatorId(),operator.getOperatorName(),operator.getOperatorNIC(),operator.getOperatorAddress(),operator.getOperatorUserName(),operator.getOperatorPassword())),
                        new MealDTO(meal.getMealId(),meal.getMealName(),meal.getMealCategory(),meal.getMealUnitPrice())
                ));


//                System.out.println(chefQueueBO.getLastChefQueue().getOrderdQty());
                System.out.println(chefQueueBO.getAllChefQueue().size());
            }

            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteOrder(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
        return false;
    }

    @Override
    public List<OrderDTO> getAllOrder() throws Exception {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Orders> list;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Orders.class).list();
            session.getTransaction().commit();
        }
        for (Orders orders :list) {
            orderDTOS.add(new OrderDTO(orders.getOrderId(),orders.getOrderDate(),
                    new CustomerDTO(orders.getCustomer().getCustomerId(),orders.getCustomer().getCustomerName(),orders.getCustomer().getCustomerAddress(),orders.getCustomer().getTelephone_no()),
                    new OperatorDTO(orders.getOperator().getOperatorId(),orders.getOperator().getOperatorName(),orders.getOperator().getOperatorNIC(),orders.getOperator().getOperatorAddress(),orders.getOperator().getOperatorUserName(),orders.getOperator().getOperatorPassword())));
        }
        return orderDTOS;
    }
}
