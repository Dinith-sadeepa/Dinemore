package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.DeliveryQueueBO;
import lk.ijse.dinemore.dto.OrderDetailDTO;

import java.util.LinkedList;
import java.util.List;

public class DeliveryQueueBOImpl implements DeliveryQueueBO {
    private LinkedList<OrderDetailDTO> detailDTOLinkedList;
    private static DeliveryQueueBO deliveryQueueBO;

    public DeliveryQueueBOImpl() {
        detailDTOLinkedList = new LinkedList<>();
    }
    public static DeliveryQueueBO getInstance() {
        if (deliveryQueueBO == null) {
            deliveryQueueBO = new DeliveryQueueBOImpl();
        }
        return deliveryQueueBO;
    }

    @Override
    public void addFirstDeliveryQueue(OrderDetailDTO orderDetail) throws Exception {
        detailDTOLinkedList.addFirst(orderDetail);
    }

    @Override
    public void addLastDeliveryQueue(OrderDetailDTO orderDetail) throws Exception {
        detailDTOLinkedList.addLast(orderDetail);
    }

    @Override
    public OrderDetailDTO getLastDeliveryQueue() throws Exception {
        return detailDTOLinkedList.pollLast();
    }

    @Override
    public List<OrderDetailDTO> getAllDeliveryQueue() throws Exception {
        return detailDTOLinkedList;
    }
}
