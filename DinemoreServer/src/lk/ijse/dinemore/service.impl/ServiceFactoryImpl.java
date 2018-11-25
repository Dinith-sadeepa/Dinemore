package lk.ijse.dinemore.service.impl;

import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.SuperService;
import lk.ijse.dinemore.service.custom.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {
    public static ServiceFactory serviceFactory;

    private ServiceFactoryImpl() throws RemoteException {

    }

    public static ServiceFactory getInstance() throws RemoteException {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case CUSTOMER:
                return new CustomerServiceImpl();
            case ORDERS:
                return new OrderServiceImpl();
            case OPERATOR:
                return new OperatorServiceImpl();
            case CHEF:
                return new ChefServiceImpl();
            case MEAL:
                return new MealServiceImpl();
            case LOG_IN:
                return new LogInServiceImpl();
            case DELIVERY_BOY:
                return new DeliveryBoyServiceImpl();
            case CHEF_QUEUE:
                return new ChefQueueServiceImpl();
            case COOKING:
                return new CookingServiceImpl();
            case ORDER_DETAIL:
                return new OrderDetailServiceImpl();
            case DELIVERY:
                return new DeliveryServiceImpl();
            case DELIVERY_QUEUE:
                return new DeliveryQueueServiceImpl();
            default:
                return null;
        }

    }
}
