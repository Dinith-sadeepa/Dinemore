package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    public boolean addOrder(OrderDTO orderDTO) throws Exception;

    public boolean deleteOrder(int id) throws Exception;

    public boolean updateOrder(OrderDTO orderDTO) throws Exception;

    public List<OrderDTO> getAllOrder() throws Exception;
}
