package lk.ijse.dinemore.business;

import lk.ijse.dinemore.business.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    public enum BOTypes {
        CUSTOMER, MEAL, ORDERS, CHEF, OPERATOR, DELIVERY_BOY, LOG_IN, ORDER_DETAIL, CHEF_QUEUE, COOKING, DELIVERY
    }

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBOTypes(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ORDERS:
                return (T) new OrdersBOImpl();
            case MEAL:
                return (T) new MealBOImpl();
            case CHEF:
                return (T) new ChefBOImpl();
            case OPERATOR:
                return (T) new OperatorBOImpl();
            case DELIVERY_BOY:
                return (T) new DeliveryBoyBOImpl();
            case LOG_IN:
                return (T) new LogInBOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailBOImpl();
            case CHEF_QUEUE:
                return (T) new ChefBOImpl();
            case COOKING:
                return (T) new CookingBOImpl();
            case DELIVERY:
                return (T) new DeliveryBOImpl();
            default:
                return null;
        }
    }
}
