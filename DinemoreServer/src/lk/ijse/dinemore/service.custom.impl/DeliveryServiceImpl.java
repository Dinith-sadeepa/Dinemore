package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.business.BOFactory;
import lk.ijse.dinemore.business.custom.DeliveryBO;
import lk.ijse.dinemore.dto.DeliveryDTO;
import lk.ijse.dinemore.service.custom.DeliveryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DeliveryServiceImpl extends UnicastRemoteObject implements DeliveryService {
    private DeliveryBO deliveryBO;
    public DeliveryServiceImpl() throws RemoteException {
        deliveryBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.DELIVERY);
    }


    @Override
    public boolean addDelivery(DeliveryDTO deliveryDTO) throws Exception {
        return deliveryBO.addDelivery(deliveryDTO);
    }

    @Override
    public boolean deleteDelivery(int id) throws Exception {
        return false;
    }

    @Override
    public boolean updateDelivery(DeliveryDTO deliveryDTO) throws Exception {
        return false;
    }

    @Override
    public List<DeliveryDTO> getAllDelivery() throws Exception {
        return deliveryBO.getAllDelivery();
    }
}
