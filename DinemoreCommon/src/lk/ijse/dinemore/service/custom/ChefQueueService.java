package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface ChefQueueService extends SuperService {
    public void addFirstChefQueue(OrderDetailDTO orderDetail) throws Exception;

    void addLastChefQueue(OrderDetailDTO orderDetail) throws Exception;

    public OrderDetailDTO getLastChefQueue() throws Exception;



    public List<OrderDetailDTO> getAllChefQueue() throws Exception;
}
