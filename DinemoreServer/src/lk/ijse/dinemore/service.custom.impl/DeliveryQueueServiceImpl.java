package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.custom.DeliveryQueueBO;
import lk.ijse.dinemore.business.custom.impl.DeliveryQueueBOImpl;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.service.custom.DeliveryQueueService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DeliveryQueueServiceImpl extends UnicastRemoteObject implements DeliveryQueueService {
    private DeliveryQueueBO deliveryQueueBO;
    public DeliveryQueueServiceImpl() throws RemoteException {
        deliveryQueueBO = DeliveryQueueBOImpl.getInstance();
    }

    @Override
    public void addFirstDeliveryQueue(OrderDetailDTO orderDetail) throws Exception {
        deliveryQueueBO.addFirstDeliveryQueue(orderDetail);
    }

    @Override
    public void addLastDeliveryQueue(OrderDetailDTO orderDetail) throws Exception {
        deliveryQueueBO.addLastDeliveryQueue(orderDetail);
    }

    @Override
    public OrderDetailDTO getLastDeliveryQueue() throws Exception {
        return deliveryQueueBO.getLastDeliveryQueue();
    }

    @Override
    public List<OrderDetailDTO> getAllDeliveryQueue() throws Exception {
        return deliveryQueueBO.getAllDeliveryQueue();
    }
}
