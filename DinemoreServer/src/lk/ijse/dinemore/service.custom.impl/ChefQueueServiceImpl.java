package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.ChefQueueBO;
import lk.ijse.dinemore.business.custom.impl.ChefQueueBOImpl;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.service.custom.ChefQueueService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ChefQueueServiceImpl extends UnicastRemoteObject implements ChefQueueService {
    private ChefQueueBO chefQueueBO;
    private ChefQueueBOImpl chefQueueBOImpl;
    public ChefQueueServiceImpl() throws RemoteException {
//        chefQueueBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CHEF_QUEUE);
        chefQueueBOImpl = ChefQueueBOImpl.getInstance();
    }

    @Override
    public void addFirstChefQueue(OrderDetailDTO orderDetail) throws Exception {
        chefQueueBOImpl.addFirstChefQueue(orderDetail);
    }

    @Override
    public void addLastChefQueue(OrderDetailDTO orderDetail) throws Exception {
        chefQueueBOImpl.addLastChefQueue(orderDetail);
    }

    @Override
    public OrderDetailDTO getLastChefQueue() throws Exception {
        return chefQueueBOImpl.getLastChefQueue();
    }

    @Override
    public List<OrderDetailDTO> getAllChefQueue() throws Exception {
//        System.out.println(chefQueueBOImpl.getAllChefQueue().size());
        return chefQueueBOImpl.getAllChefQueue();
    }
}
