package lk.ijse.dinemore.business.custom.impl;

import lk.ijse.dinemore.business.custom.ChefQueueBO;
import lk.ijse.dinemore.dto.OrderDetailDTO;

import java.util.LinkedList;
import java.util.List;

public class ChefQueueBOImpl implements ChefQueueBO {
    private LinkedList<OrderDetailDTO> chefOrdersQueue;
    private static ChefQueueBOImpl chefQueueBOImpl;

    private ChefQueueBOImpl() {
        chefOrdersQueue = new LinkedList<>();
    }

    public static ChefQueueBOImpl getInstance() {
        if (chefQueueBOImpl == null) {
            chefQueueBOImpl = new ChefQueueBOImpl();
        }
        return chefQueueBOImpl;
    }

    @Override
    public void addFirstChefQueue(OrderDetailDTO orderDetail) throws Exception {
        chefOrdersQueue.addFirst(orderDetail);
    }

    @Override
    public void addLastChefQueue(OrderDetailDTO orderDetail) throws Exception {
        chefOrdersQueue.addLast(orderDetail);
    }

    @Override
    public OrderDetailDTO getLastChefQueue() throws Exception {
        return chefOrdersQueue.pollLast();
    }

    @Override
    public List<OrderDetailDTO> getAllChefQueue() throws Exception {
        return chefOrdersQueue;
    }
}
