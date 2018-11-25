package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.OrderDTO;

import java.util.List;

public interface OrdersBO extends SuperBO {
    public boolean addOrder(OrderDTO orderDTO) throws Exception;

    public boolean deleteOrder(int id) throws Exception;

    public boolean updateOrder(OrderDTO orderDTO) throws Exception;

    public List<OrderDTO> getAllOrder() throws Exception;
}
