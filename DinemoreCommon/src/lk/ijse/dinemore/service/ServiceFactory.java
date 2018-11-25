package lk.ijse.dinemore.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes {
        CUSTOMER, MEAL, ORDERS, CHEF, OPERATOR, DELIVERY_BOY, LOG_IN, CHEF_QUEUE, COOKING, ORDER_DETAIL, DELIVERY,DELIVERY_QUEUE

    }

    public SuperService getService(ServiceTypes type) throws Exception;
}
