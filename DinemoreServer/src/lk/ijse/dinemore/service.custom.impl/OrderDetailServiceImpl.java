package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.OrderDetailBO;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.entity.*;
import lk.ijse.dinemore.service.custom.OrderDetailService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OrderDetailServiceImpl extends UnicastRemoteObject implements OrderDetailService {
    private OrderDetailBO orderDetailBO;
    public OrderDetailServiceImpl() throws RemoteException {
        orderDetailBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ORDER_DETAIL);
    }

    @Override
    public boolean addOrderDetail(OrderDetailDTO orderDetail) throws Exception {
        return orderDetailBO.addOrderDetail(orderDetail);
    }

    @Override
    public boolean deleteOrderDetail(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetail() throws Exception {
        return orderDetailBO.getAllOrderDetail();
    }
}
