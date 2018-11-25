package lk.ijse.dinemore.business.custom;

import lk.ijse.dinemore.business.SuperBO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.entity.OrderDetail;

import java.util.List;

public interface OrderDetailBO extends SuperBO {
    public boolean addOrderDetail(OrderDetailDTO orderDetail) throws Exception;

    public boolean deleteOrderDetail(int id) throws Exception;

    public boolean updateOrderDetail(OrderDetailDTO customerDTO) throws Exception;

    public List<OrderDetailDTO> getAllOrderDetail() throws Exception;
}
