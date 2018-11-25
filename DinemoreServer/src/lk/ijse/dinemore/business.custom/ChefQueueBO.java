package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.entity.OrderDetail;

import java.util.List;

public interface ChefQueueBO extends SuperBO {
    public void addFirstChefQueue(OrderDetailDTO orderDetail) throws Exception;

    void addLastChefQueue(OrderDetailDTO orderDetail) throws Exception;

    public OrderDetailDTO getLastChefQueue() throws Exception;

    public List<OrderDetailDTO> getAllChefQueue() throws Exception;
}
