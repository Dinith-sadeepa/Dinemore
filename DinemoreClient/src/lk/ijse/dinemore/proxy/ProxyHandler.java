package lk.ijse.dinemore.proxy;

import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.SuperService;
import lk.ijse.dinemore.service.custom.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandler implements ServiceFactory {
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;
    private CustomerService customerService;
    private OperatorService operatorService;
    private ChefService chefService;
    private OrderService orderService;
    private MealService mealService;
    private DeliveryBoyService deliveryBoyService;
    private LogInService logInService;
    private ChefQueueService chefQueueService;
    private CookingService cookingService;
    private OrderDetailService orderDetailService;
    private DeliveryService deliveryService;
    private DeliveryQueueService deliveryQueueService;

    public ProxyHandler() {
        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/dinemore");


            customerService = (CustomerService) serviceFactory.getService(ServiceTypes.CUSTOMER);
            operatorService = (OperatorService) serviceFactory.getService(ServiceTypes.OPERATOR);
            chefService = (ChefService) serviceFactory.getService(ServiceTypes.CHEF);
            orderService = (OrderService) serviceFactory.getService(ServiceTypes.ORDERS);
            mealService = (MealService) serviceFactory.getService(ServiceTypes.MEAL);
            deliveryBoyService = (DeliveryBoyService) serviceFactory.getService(ServiceTypes.DELIVERY_BOY);
            logInService = (LogInService) serviceFactory.getService(ServiceTypes.LOG_IN);
            chefQueueService = (ChefQueueService) serviceFactory.getService(ServiceTypes.CHEF_QUEUE);
            cookingService = (CookingService) serviceFactory.getService(ServiceTypes.COOKING);
            orderDetailService = (OrderDetailService) serviceFactory.getService(ServiceTypes.ORDER_DETAIL);
            deliveryService = (DeliveryService) serviceFactory.getService(ServiceTypes.DELIVERY);
            deliveryQueueService = (DeliveryQueueService) serviceFactory.getService(ServiceTypes.DELIVERY_QUEUE);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ProxyHandler getInstance() {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes type) throws Exception {
        switch (type) {
            case CUSTOMER:
                return customerService;
            case ORDERS:
                return orderService;
            case OPERATOR:
                return operatorService;
            case  CHEF:
                return chefService;
            case MEAL:
                return mealService;
            case LOG_IN:
                return logInService;
            case DELIVERY_BOY:
                return deliveryBoyService;
            case CHEF_QUEUE:
                return chefQueueService;
            case COOKING:
                return cookingService;
            case ORDER_DETAIL:
                return orderDetailService;
            case DELIVERY:
                return deliveryService;
            case DELIVERY_QUEUE:
                return deliveryQueueService;
            default:
                return null;
        }
    }
}
