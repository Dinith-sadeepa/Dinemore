package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.DeliveryBO;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.entity.Cooking;
import lk.ijse.dinemore.entity.Delivery;
import lk.ijse.dinemore.entity.DeliveryBoy;
import lk.ijse.dinemore.entity.OrderDetail;
import lk.ijse.dinemore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {
    private SessionFactory sessionFactory;

    public DeliveryBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addDelivery(DeliveryDTO deliveryDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            DeliveryBoy deliveryBoy = session.get(DeliveryBoy.class, deliveryDTO.getDeliveryBoy().getDeliveryBoyId());
            OrderDetail orderDetail = session.get(OrderDetail.class, deliveryDTO.getOrderDetail().getOrderDetailId());
            Delivery delivery = new Delivery(deliveryDTO.getStartTime(), deliveryDTO.getEndTime(),
                    orderDetail,
                    deliveryBoy);

            boolean isAdded = (session.save(delivery) != null);


            session.getTransaction().commit();
            return isAdded;
        }
    }

    @Override
    public boolean deleteDelivery(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateDelivery(DeliveryDTO deliveryDTO) throws Exception {
        return false;
    }

    @Override
    public List<DeliveryDTO> getAllDelivery() throws Exception {
        List<Delivery> list;
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createCriteria(Delivery.class).list();
            session.getTransaction().commit();
        }
        for (Delivery delivery : list) {
            deliveryDTOS.add(new DeliveryDTO(delivery.getDeliveryId(), delivery.getStartTime(), delivery.getEndTime(),
                    new OrderDetailDTO(delivery.getOrderDetail().getOrderDetailId(), delivery.getOrderDetail().getOrderdQty(), delivery.getOrderDetail().getPrice(),
                            new OrderDTO(delivery.getOrderDetail().getOrders().getOrderId(), delivery.getOrderDetail().getOrders().getOrderDate(),
                                    new CustomerDTO(delivery.getOrderDetail().getOrders().getCustomer().getCustomerId(), delivery.getOrderDetail().getOrders().getCustomer().getCustomerName(), delivery.getOrderDetail().getOrders().getCustomer().getCustomerAddress(), delivery.getOrderDetail().getOrders().getCustomer().getTelephone_no()),
                                    new OperatorDTO(delivery.getOrderDetail().getOrders().getOperator().getOperatorId(), delivery.getOrderDetail().getOrders().getOperator().getOperatorName(), delivery.getOrderDetail().getOrders().getOperator().getOperatorNIC(), delivery.getOrderDetail().getOrders().getOperator().getOperatorAddress(), delivery.getOrderDetail().getOrders().getOperator().getOperatorUserName(), delivery.getOrderDetail().getOrders().getOperator().getOperatorPassword())),
                            new MealDTO(delivery.getOrderDetail().getMeal().getMealId(), delivery.getOrderDetail().getMeal().getMealName(), delivery.getOrderDetail().getMeal().getMealCategory(), delivery.getOrderDetail().getMeal().getMealUnitPrice())),
                    new DeliveryBoyDTO(delivery.getDeliveryBoy().getDeliveryBoyId(), delivery.getDeliveryBoy().getDeliveryBoyName(), delivery.getDeliveryBoy().getDeliveryBoyNIC(), delivery.getDeliveryBoy().getDeliveryBoyAddress(), delivery.getDeliveryBoy().getDeliveryBoyUserName(), delivery.getDeliveryBoy().getDeliveryBoyPassword())));
        }
        return deliveryDTOS;
    }
}
