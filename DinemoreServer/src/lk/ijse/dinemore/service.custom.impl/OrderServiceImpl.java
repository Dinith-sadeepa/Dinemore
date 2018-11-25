package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.OrdersBO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.service.custom.OrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {
    private OrdersBO ordersBO;
    public OrderServiceImpl() throws RemoteException {
        ordersBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ORDERS);
    }

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws Exception {
        return ordersBO.addOrder(orderDTO);
    }

    @Override
    public boolean deleteOrder(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
        return false;
    }

    @Override
    public List<OrderDTO> getAllOrder() throws Exception {
        return ordersBO.getAllOrder();
    }
}
