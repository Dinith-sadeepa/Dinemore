package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.CookingBO;
import lk.ijse.dinemore.business.custom.DeliveryQueueBO;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.entity.*;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CookingBOImpl implements CookingBO {
    private SessionFactory sessionFactory;
    private DeliveryQueueBO deliveryQueueBO;

    public CookingBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        deliveryQueueBO = DeliveryQueueBOImpl.getInstance();
    }

    @Override
    public boolean addCooking(CookingDTO cookingDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Chef chef = session.get(Chef.class, cookingDTO.getChefDTO().getChefId());
            OrderDetail orderDetail = session.get(OrderDetail.class, cookingDTO.getOrderDetailDTO().getOrderDetailId());
            Cooking cooking = new Cooking(cookingDTO.getStartTime(), cookingDTO.getEndTime(),
                    orderDetail,
                    chef);

            Orders orders = session.get(Orders.class, orderDetail.getOrders().getOrderId());
            Customer customer = session.get(Customer.class, orderDetail.getOrders().getCustomer().getCustomerId());
            Operator operator = session.get(Operator.class, orderDetail.getOrders().getOperator().getOperatorId());
            Meal meal = session.get(Meal.class, orderDetail.getMeal().getMealId());
            boolean isAdded = (session.save(cooking) != null);

            deliveryQueueBO.addFirstDeliveryQueue(new OrderDetailDTO(orderDetail.getOrderDetailId(), orderDetail.getOrderdQty(), orderDetail.getPrice(),
                    new OrderDTO(orders.getOrderId(), orders.getOrderDate(),
                            new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getTelephone_no()),
                            new OperatorDTO(operator.getOperatorId(), operator.getOperatorName(), operator.getOperatorNIC(), operator.getOperatorAddress(), operator.getOperatorUserName(), operator.getOperatorPassword())),
                    new MealDTO(meal.getMealId(), meal.getMealName(), meal.getMealCategory(), meal.getMealUnitPrice())));
            System.out.println(deliveryQueueBO.getAllDeliveryQueue().size());

            session.getTransaction().commit();
            return isAdded;
        }
    }

    @Override
    public boolean deleteCooking(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateCooking(CookingDTO cookingDTO) throws Exception {
        return false;
    }

    @Override
    public List<CookingDTO> getAllCooking() throws Exception {
        List<Cooking> list;
        List<CookingDTO> cookingDTOS = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Cooking.class).list();
            session.getTransaction().commit();
        }
        for (Cooking cooking : list) {
            cookingDTOS.add(new CookingDTO(cooking.getCookingId(), cooking.getStartTime(), cooking.getEndTime(),
                    new ChefDTO(cooking.getChef().getChefId(), cooking.getChef().getChefName(), cooking.getChef().getChefNIC(), cooking.getChef().getChefAddress(), cooking.getChef().getChefUserName(), cooking.getChef().getChefPassword()),
                    new OrderDetailDTO(cooking.getOrderDetail().getOrderDetailId(), cooking.getOrderDetail().getOrderdQty(), cooking.getOrderDetail().getPrice(),
                            new OrderDTO(cooking.getOrderDetail().getOrders().getOrderId(), cooking.getOrderDetail().getOrders().getOrderDate(),
                                    new CustomerDTO(cooking.getOrderDetail().getOrders().getCustomer().getCustomerId(), cooking.getOrderDetail().getOrders().getCustomer().getCustomerName(), cooking.getOrderDetail().getOrders().getCustomer().getCustomerAddress(), cooking.getOrderDetail().getOrders().getCustomer().getTelephone_no()),
                                    new OperatorDTO(cooking.getOrderDetail().getOrders().getOperator().getOperatorId(), cooking.getOrderDetail().getOrders().getOperator().getOperatorName(), cooking.getOrderDetail().getOrders().getOperator().getOperatorNIC(), cooking.getOrderDetail().getOrders().getOperator().getOperatorAddress(), cooking.getOrderDetail().getOrders().getOperator().getOperatorUserName(), cooking.getOrderDetail().getOrders().getOperator().getOperatorPassword())),
                            new MealDTO(cooking.getOrderDetail().getMeal().getMealId(), cooking.getOrderDetail().getMeal().getMealName(), cooking.getOrderDetail().getMeal().getMealCategory(), cooking.getOrderDetail().getMeal().getMealUnitPrice()))));
        }
        return cookingDTOS;
    }
}
