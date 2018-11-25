package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface DeliveryQueueService extends SuperService {
    public void addFirstDeliveryQueue(OrderDetailDTO orderDetail) throws Exception;

    void addLastDeliveryQueue(OrderDetailDTO orderDetail) throws Exception;

    public OrderDetailDTO getLastDeliveryQueue() throws Exception;

    public List<OrderDetailDTO> getAllDeliveryQueue() throws Exception;
}
