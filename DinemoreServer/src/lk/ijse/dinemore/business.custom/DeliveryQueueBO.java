package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.DeliveryDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;

import java.util.List;

public interface DeliveryQueueBO extends SuperBO {
    public void addFirstDeliveryQueue(OrderDetailDTO orderDetail) throws Exception;

    void addLastDeliveryQueue(OrderDetailDTO orderDetail) throws Exception;

    public OrderDetailDTO getLastDeliveryQueue() throws Exception;

    public List<OrderDetailDTO> getAllDeliveryQueue() throws Exception;
}
