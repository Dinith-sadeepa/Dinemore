package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.List;

public interface OrderDetailService extends SuperService {
    public boolean addOrderDetail(OrderDetailDTO orderDetail) throws Exception;

    public boolean deleteOrderDetail(int id) throws Exception;

    public boolean updateOrderDetail(OrderDetailDTO customerDTO) throws Exception;

    public List<OrderDetailDTO> getAllOrderDetail() throws Exception;
}
